package com.bangkit.bahanbaku.ui.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.di.DatabaseModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val profileRepository: ProfileRepository) : ViewModel() {
    fun register(username: String, email: String, password: String) =
        profileRepository.register(username, email, password)
}