package com.bangkit.bahanbaku.ui.snapfood

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bangkit.bahanbaku.data.repository.FoodRepository
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.di.DatabaseModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SnapFoodViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    private val profileRepository: ProfileRepository
    ) : ViewModel() {

    fun getToken(): LiveData<String> {
        return profileRepository.getToken()
    }
}