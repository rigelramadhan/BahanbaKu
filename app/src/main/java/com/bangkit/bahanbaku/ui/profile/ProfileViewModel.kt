package com.bangkit.bahanbaku.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    fun getProfile(token: String) = profileRepository.getProfile(token)

    fun updateProfile(token: String, username: String, email: String, password: String) =
        profileRepository.updateUser(token, username, email, password)

    fun uploadPicture(token: String, file: File) = profileRepository.uploadPicture(token, file)

    fun getToken() = profileRepository.getToken()

    fun deleteToken() = profileRepository.deleteToken()
}