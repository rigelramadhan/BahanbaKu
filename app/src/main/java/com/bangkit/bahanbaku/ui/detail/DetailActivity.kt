package com.bangkit.bahanbaku.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        binding.tvFeaturedRecipe.text = recipe?.name
        binding.tvDescription.text = recipe?.description

        Glide.with(this)
            .load(recipe?.photoUrl)
            .into(binding.imgFeaturedRecipe)
    }

    companion object {
        const val EXTRA_RECIPE = "extra_recipe"
    }
}