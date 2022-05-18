package com.bangkit.bahanbaku.ui.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.di.AppModule

class RegisterViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    fun register(username: String, email: String, password: String) =
        profileRepository.register(username, email, password)

    class RegisterViewModelFactory private constructor(private val profileRepository: ProfileRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                return RegisterViewModel(profileRepository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }

        companion object {
            @Volatile
            private var instance: RegisterViewModelFactory? = null

            fun getInstance(context: Context): RegisterViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: RegisterViewModelFactory(AppModule.provideProfileRepository(context))
                }.also { instance = it }
        }
    }
}