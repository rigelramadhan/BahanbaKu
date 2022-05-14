package com.bangkit.bahanbaku.ui.snaprecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.bahanbaku.databinding.ActivitySnapRecipeBinding

class SnapRecipeActivity : AppCompatActivity() {

    private val binding: ActivitySnapRecipeBinding by lazy {
        ActivitySnapRecipeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}