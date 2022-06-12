package com.bangkit.bahanbaku.ui.login

import androidx.lifecycle.ViewModel
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ViewModel() {
    fun login(email: String, password: String) = profileRepository.login(email, password)

    fun saveToken(token: String) {
        profileRepository.saveToken(token)
    }

    fun isFirstTime() = profileRepository.isFirstTime()

    fun setFirstTime(firstTime: Boolean) = profileRepository.setFirstTime(firstTime)
}
