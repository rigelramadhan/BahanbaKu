package com.bangkit.bahanbaku.ui.snapfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.bahanbaku.databinding.ActivitySnapFoodBinding

class SnapFoodActivity : AppCompatActivity() {

    private val binding: ActivitySnapFoodBinding by lazy {
        ActivitySnapFoodBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}