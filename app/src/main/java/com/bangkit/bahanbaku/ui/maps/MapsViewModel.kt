package com.bangkit.bahanbaku.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel@Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    fun getToken(): LiveData<String> {
        return profileRepository.getToken().asLiveData()
    }
}