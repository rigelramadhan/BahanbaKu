package com.bangkit.bahanbaku.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.di.DatabaseModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ViewModel() {
    fun login(email: String, password: String) = profileRepository.login(email, password)

    fun saveToken(token: String) {
        profileRepository.saveToken(token)
    }
}
