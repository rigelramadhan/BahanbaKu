package com.bangkit.bahanbaku.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.R
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
    private var token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getToken()
    }

    private fun getToken() {
        viewModel.getToken().observe(this) {
            if (it.length <= 5) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                val token = "Bearer $it"
                this.token = token
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchMenuItem = menu.findItem(R.id.search)
        val searchView = searchMenuItem.actionView as SearchView
        searchMenuItem.expandActionView()

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = getString(R.string.search_for_recipes)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    if (token.isNotEmpty()) {
                        searchRecipe(token, query)
                    }
                }
                searchMenuItem.expandActionView()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun searchRecipe(token: String, query: String) {
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
}