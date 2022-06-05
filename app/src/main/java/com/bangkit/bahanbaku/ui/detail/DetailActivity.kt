package com.bangkit.bahanbaku.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.adapter.DetailItemAdapter
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.databinding.ActivityDetailBinding
import com.bangkit.bahanbaku.ui.ingredient.IngredientActivity
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.utils.Result
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailViewModel by viewModels()
    private var isRecipeBookmarked = false
    private var loadingState = false
    private var recipe: RecipeEntity? = null
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getToken()
    }

    private fun getToken() {
        viewModel.getToken().observe(this) {
            if (it == "null") {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                token = "Bearer $it"
                setupView(token!!)
            }
        }
    }

    private fun setupView(token: String) {
        val recipeId = intent.getStringExtra(EXTRA_RECIPE_ID)
        if (recipeId != null) {
            viewModel.getRecipe(token, recipeId).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is Result.Error -> {
                        binding.progressBar.isVisible = false
                        val error = result.error
                        Log.d(TAG, error)
                    }
                    is Result.Success -> {
                        binding.progressBar.isVisible = false
                        val recipe = result.data
                        this.recipe = recipe
                        checkIfRecipeBookmarked(token, recipe.id)

                        binding.tvRecipe.text = recipe.title
                        binding.tvDescription.text = recipe.description
                        binding.tvServings.text =
                            getString(R.string.serving).format(recipe.servings)

                        binding.rvIngredients.apply {
                            adapter = DetailItemAdapter(recipe.ingredients)
                            layoutManager = LinearLayoutManager(this@DetailActivity)
                        }

                        binding.rvInstructions.apply {
                            adapter = DetailItemAdapter(recipe.steps)
                            layoutManager = LinearLayoutManager(this@DetailActivity)
                        }

                        Glide.with(this)
                            .load(recipe.image)
                            .into(binding.imgRecipe)
                    }
                }
            }
        }

        binding.btnCheckIngredients.setOnClickListener {
            val ingredients = recipe?.ingredients
            if (ingredients != null) {
                val cleansedList = cleanseIngredients(ingredients)
                val arrayList = arrayListOf<String>().addAll(cleansedList)
                val intent = Intent(this, IngredientActivity::class.java)
                intent.putExtra(IngredientActivity.EXTRA_SEARCH, arrayList)
                startActivity(intent)
            }
        }
    }

    private fun cleanseIngredients(list: List<String>): List<String> {
        val mutableList = mutableListOf<String>()
        list.forEach { s ->
            val separatedString = s.split(",")
            val ingredientAndAmountString = separatedString[0]
            val cleansedString =
                "${ingredientAndAmountString[2]} ${ingredientAndAmountString[0]} ${ingredientAndAmountString[1]}"

            mutableList.add(cleansedString)
        }

        return mutableList
    }

    private fun checkIfRecipeBookmarked(token: String, id: String) {
        viewModel.checkIfRecipeBookmarked(token, id).observe(this) {
            isRecipeBookmarked = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_item_recipe, menu)
        if (isRecipeBookmarked) {
            menu.findItem(R.id.bookmark).icon = AppCompatResources.getDrawable(
                this,
                R.drawable.ic_baseline_bookmark_24
            )
        } else {
            menu.findItem(R.id.bookmark).icon = AppCompatResources.getDrawable(
                this,
                R.drawable.ic_baseline_bookmark_border_24
            )
        }


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bookmark -> {
                if (!isRecipeBookmarked) {
                    addBookmark(item)
                } else {
                    deleteBookmark(item)
                }

                isRecipeBookmarked = !isRecipeBookmarked
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteBookmark(item: MenuItem) {
        if (!loadingState) {
            loadingState = true
            viewModel.deleteBookmark(token as String, (recipe as RecipeEntity).id)
                .observe(this) { result ->
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar.isVisible = true
                        }
                        is Result.Error -> {
                            binding.progressBar.isVisible = false
                            val error = result.error
                            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                        }
                        is Result.Success -> {
                            binding.progressBar.isVisible = false
                            val successStatus = result.data.success
                            loadingState = false

                            item.icon = AppCompatResources.getDrawable(
                                this,
                                R.drawable.ic_baseline_bookmark_border_24
                            )
                        }
                    }
                }
        }
    }

    private fun addBookmark(item: MenuItem) {
        if (!loadingState) {
            loadingState = true
            viewModel.addBookmark(token as String, (recipe as RecipeEntity).id)
                .observe(this) { result ->
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar.isVisible = true
                        }
                        is Result.Error -> {
                            val error = result.error
                            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                        }
                        is Result.Success -> {
                            binding.progressBar.isVisible = false
                            val successStatus = result.data.success
                            loadingState = false

                            item.icon = AppCompatResources.getDrawable(
                                this,
                                R.drawable.ic_baseline_bookmark_24
                            )
                        }
                    }
                }
        }
    }

    companion object {
        private const val TAG = "DetailActivity.log"
        const val EXTRA_RECIPE_ID = "extra_recipe_id"
    }
}