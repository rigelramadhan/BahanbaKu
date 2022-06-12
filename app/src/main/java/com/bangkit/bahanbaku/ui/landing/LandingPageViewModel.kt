package com.bangkit.bahanbaku.ui.landing

import androidx.lifecycle.ViewModel
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingPageViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    fun setFirstTime(firstTime: Boolean) = profileRepository.setFirstTime(firstTime)
}