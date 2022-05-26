package com.bangkit.bahanbaku.ui.ingredient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bangkit.bahanbaku.databinding.ActivityIngredientBinding
import com.bangkit.bahanbaku.ui.login.LoginActivity

class IngredientActivity : AppCompatActivity() {

    private val binding: ActivityIngredientBinding by lazy {
        ActivityIngredientBinding.inflate(layoutInflater)
    }

    private val viewModel: IngredientViewModel by viewModels()

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
        val ingredients = intent.getStringArrayListExtra(EXTRA_SEARCH)
        if (ingredients != null) {
            viewModel.getIngredients(token, ingredients.toList())
        }
    }

    companion object {
        const val EXTRA_SEARCH = "extra_search"
    }
}