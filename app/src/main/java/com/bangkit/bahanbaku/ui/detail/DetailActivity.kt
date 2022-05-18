package com.bangkit.bahanbaku.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.adapter.DetailItemAdapter
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        val recipe = intent.getParcelableExtra<RecipeEntity>(EXTRA_RECIPE)
        if (recipe != null) {
            binding.tvFeaturedRecipe.text = recipe.title
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
                .into(binding.imgFeaturedRecipe)
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
        const val EXTRA_RECIPE = "extra_recipe"
    }
}