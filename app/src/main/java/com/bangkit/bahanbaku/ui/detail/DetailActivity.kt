package com.bangkit.bahanbaku.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.adapter.DetailItemAdapter
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.databinding.ActivityDetailBinding
import com.bangkit.bahanbaku.utils.Result
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.DetailViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val token = ""
        setupView(token)
    }

    private fun setupView(token: String) {
        val recipeId = intent.getStringExtra(EXTRA_RECIPE_ID)
        if (recipeId != null) {
            viewModel.getRecipe(token, recipeId).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {}
                    is Result.Error -> {}
                    is Result.Success -> {
                        val recipe = result.data

                        binding.tvRecipe.text = recipe.title
                        binding.tvDescription.text = recipe.description
                        binding.tvServings.text = "${recipe.servings} servings"

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

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_item_recipe, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bookmark -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_RECIPE_ID = "extra_recipe_id"
    }
}