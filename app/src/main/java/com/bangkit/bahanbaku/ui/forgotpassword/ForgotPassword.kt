package com.bangkit.bahanbaku.ui.forgotpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.bahanbaku.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
    }
}