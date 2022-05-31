package com.bangkit.bahanbaku.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
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
                setupView(it)
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
                            is Result.Loading -> {}
                            is Result.Error -> {}
                            is Result.Success -> {
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