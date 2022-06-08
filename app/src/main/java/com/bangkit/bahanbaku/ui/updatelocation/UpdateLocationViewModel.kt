package com.bangkit.bahanbaku.ui.updatelocation

import androidx.lifecycle.ViewModel
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateLocationViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    fun getToken() = profileRepository.getToken()

    fun updateLocation(token: String, lat: Double, lng: Double) =
        profileRepository.updateLocation(token, lng, lat)
}