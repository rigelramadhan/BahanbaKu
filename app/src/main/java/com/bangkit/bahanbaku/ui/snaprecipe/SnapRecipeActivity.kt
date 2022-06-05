package com.bangkit.bahanbaku.ui.snaprecipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.adapter.SearchRecipeAdapter
import com.bangkit.bahanbaku.databinding.ActivitySnapRecipeBinding
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SnapRecipeActivity : AppCompatActivity() {

    private val binding: ActivitySnapRecipeBinding by lazy {
        ActivitySnapRecipeBinding.inflate(layoutInflater)
    }

    private val viewModel: SnapRecipeViewModel by viewModels()

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
                val token = "Bearer $it"
                setupView(token)
            }
        }
    }

    private fun setupView(token: String) {
        val foodName = intent.getStringExtra(EXTRA_FOOD_NAME)
        if (!foodName.isNullOrEmpty()) {
            viewModel.getRecipes(token, foodName).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is Result.Error -> {
                        val error = result.error
                        binding.progressBar.isVisible = false
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                    }
                    is Result.Success -> {
                        binding.progressBar.isVisible = false
                        val data = result.data
                        binding.rvRecipes.apply {
                            adapter = SearchRecipeAdapter(data)
                            layoutManager = LinearLayoutManager(this@SnapRecipeActivity)
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val EXTRA_FOOD_NAME = "extra_food_name"
    }
}