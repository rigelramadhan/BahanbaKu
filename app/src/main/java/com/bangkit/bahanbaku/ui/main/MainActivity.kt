package com.bangkit.bahanbaku.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bangkit.bahanbaku.databinding.ActivityMainBinding
import com.bangkit.bahanbaku.utils.Result
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        mainViewModel.getFeaturedRecipe().observe(this) { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Error -> {

                }

                is Result.Success -> {
                    val data = result.data
                    binding.tvFeaturedRecipe.text = data.name
                    binding.tvFeaturedRecipeDescription.text = data.description
                    binding.tvFeaturedAuthor.text = data.author

                    Glide.with(this)
                        .load(data.photoUrl)
                        .into(binding.imgFeaturedRecipe)
                }
            }
        }
    }
}