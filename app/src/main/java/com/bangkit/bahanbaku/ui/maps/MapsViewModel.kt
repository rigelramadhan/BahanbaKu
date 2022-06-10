package com.bangkit.bahanbaku.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.bahanbaku.data.repository.FoodRepository
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel@Inject constructor(
    private val foodRepository: FoodRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    fun getToken(): LiveData<String> {
        return profileRepository.getToken()
    }

    fun updateLocation(token: String, lat: Double, lng: Double) = profileRepository.updateLocation(token, lng, lat)

    fun getNearby(token: String, query: String) = foodRepository.getNearbyResto(token, query)
}