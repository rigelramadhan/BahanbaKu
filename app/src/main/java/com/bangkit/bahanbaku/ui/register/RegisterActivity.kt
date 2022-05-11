package com.bangkit.bahanbaku.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.bahanbaku.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}