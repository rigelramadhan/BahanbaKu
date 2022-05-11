package com.bangkit.bahanbaku.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.bahanbaku.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}