package com.bangkit.bahanbaku.ui.snapfood.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.bahanbaku.databinding.ActivitySnapFoodResultBinding

class SnapFoodResultActivity : AppCompatActivity() {

    private val binding: ActivitySnapFoodResultBinding by lazy {
        ActivitySnapFoodResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}