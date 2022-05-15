package com.bangkit.bahanbaku.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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
            binding.tvDescription.text = recipe.desc
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
                .load(recipe.images)
                .into(binding.imgFeaturedRecipe)
        }

    }

    companion object {
        const val EXTRA_RECIPE = "extra_recipe"
    }
}