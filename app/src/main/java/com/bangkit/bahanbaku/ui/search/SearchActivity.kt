package com.bangkit.bahanbaku.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.adapter.SearchRecipeAdapter
import com.bangkit.bahanbaku.databinding.ActivitySearchBinding
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val binding: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    private val viewModel: SearchViewModel by viewModels()

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
        binding.svSearch.setOnClickListener {
            binding.svSearch.apply {
                requestFocus()
                isIconified = true
            }
        }

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.searchRecipe(token, query).observe(this@SearchActivity) { result ->
                        when (result) {
                            is Result.Loading -> {
                                binding.progressBar.isVisible = true
                            }
                            is Result.Error -> {
                                val error = result.error
                                Toast.makeText(this@SearchActivity, error, Toast.LENGTH_SHORT).show()
                                binding.progressBar.isVisible = false
                            }
                            is Result.Success -> {
                                binding.progressBar.isVisible = false
                                val data = result.data

                                binding.rvRecipes.apply {
                                    adapter = SearchRecipeAdapter(data)
                                    layoutManager = LinearLayoutManager(this@SearchActivity)
                                }
                            }
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}