package com.bangkit.bahanbaku.ui.snapfood.result

import androidx.lifecycle.ViewModel
import com.bangkit.bahanbaku.data.repository.FoodRepository
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SnapFoodResultViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {
    fun getToken() = profileRepository.getToken()

    fun snapFood(token: String, file: File) = foodRepository.postSnapFood(token, file)
}