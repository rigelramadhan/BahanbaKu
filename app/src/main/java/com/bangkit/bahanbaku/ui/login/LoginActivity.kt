package com.bangkit.bahanbaku.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isInvisible
import com.bangkit.bahanbaku.databinding.ActivityLoginBinding
import com.bangkit.bahanbaku.ui.main.MainActivity
import com.bangkit.bahanbaku.ui.register.RegisterActivity
import com.bangkit.bahanbaku.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.login(email, password).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.isInvisible = false
                    }
                    is Result.Error -> {
                        val error = result.error
                        Log.d(TAG, error)
                        binding.progressBar.isInvisible = true
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                    }
                    is Result.Success -> {
                        val data = result.data.results
                        viewModel.saveToken(data.token)
                        binding.progressBar.isInvisible = true

                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val TAG = "LoginActivity.log"
    }
}