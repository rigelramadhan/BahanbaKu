package com.bangkit.bahanbaku.ui.ingredient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.bahanbaku.databinding.ActivityIngredientBinding

class IngredientActivity : AppCompatActivity() {

    private val binding: ActivityIngredientBinding by lazy {
        ActivityIngredientBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}