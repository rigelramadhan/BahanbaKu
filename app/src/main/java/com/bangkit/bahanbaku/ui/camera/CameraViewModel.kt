package com.bangkit.bahanbaku.ui.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.repository.FoodRepository
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val repository: FoodRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {
    fun postSnapFood(token: String, file: File) = repository.postSnapFood(token, file)

    fun getToken() = profileRepository.getToken().asLiveData()
}