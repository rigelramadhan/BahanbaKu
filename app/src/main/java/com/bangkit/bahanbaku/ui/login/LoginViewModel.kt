package com.bangkit.bahanbaku.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.di.AppModule
import com.bangkit.bahanbaku.ui.register.RegisterViewModel

class LoginViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    fun login(email: String, password: String) = profileRepository.login(email, password)

    class LoginViewModelFactory private constructor(private val profileRepository: ProfileRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(profileRepository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }

        companion object {
            @Volatile
            private var instance: LoginViewModelFactory? = null

            fun getInstance(context: Context): LoginViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: LoginViewModelFactory(AppModule.provideProfileRepository(context))
                }.also { instance = it }
        }
    }
}