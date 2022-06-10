package com.bangkit.bahanbaku.ui.ingredient

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.adapter.EcommAdapter
import com.bangkit.bahanbaku.databinding.ActivityIngredientBinding
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientActivity : AppCompatActivity() {

    private val binding: ActivityIngredientBinding by lazy {
        ActivityIngredientBinding.inflate(layoutInflater)
    }

    private val viewModel: IngredientViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
        val ingredients = intent.getStringArrayListExtra(EXTRA_SEARCH)
        if (ingredients != null) {
            viewModel.getIngredients(token, ingredients.toList()).observe(this) { result ->
                when (result) {
                    is Result.Error -> {
                        val error = result.error
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                        binding.progressBar.isVisible = false
                    }

                    is Result.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is Result.Success -> {
                        binding.progressBar.isVisible = false
                        val data = result.data

                        val aboveData = data.above

                        binding.imgAboveNotFound.isVisible = aboveData.isEmpty()

                        binding.rvIngredientsEcommAbove50.apply {
                            adapter = EcommAdapter(aboveData)
                            layoutManager = LinearLayoutManager(this@IngredientActivity)
                        }

                        val belowData = data.under

                        if (belowData.isNotEmpty()) {
                            val constraintSet = ConstraintSet()
                            constraintSet.clone(binding.constraintIngredients)
                            constraintSet.connect(
                                R.id.tv_above_50,
                                ConstraintSet.TOP,
                                R.id.rv_ingredients_ecomm_under_50,
                                ConstraintSet.BOTTOM
                            )
                        }

                        binding.imgUnderNotFound.isVisible = belowData.isEmpty()

                        binding.rvIngredientsEcommUnder50.apply {
                            adapter = EcommAdapter(belowData)
                            layoutManager = LinearLayoutManager(this@IngredientActivity)
                        }
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_SEARCH = "extra_search"
    }
}