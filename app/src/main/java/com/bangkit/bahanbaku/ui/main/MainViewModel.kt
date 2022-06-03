package com.bangkit.bahanbaku.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import com.bangkit.bahanbaku.di.DatabaseModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val profileRepository: ProfileRepository) :
    ViewModel() {
    fun getToken() = profileRepository.getToken().asLiveData()
}