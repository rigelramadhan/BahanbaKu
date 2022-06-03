package com.bangkit.bahanbaku.ui.home

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
class HomeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {
    fun getRecipes(token: String) = recipeRepository.getNewRecipes(token)

    fun getFeaturedRecipe(token: String) = recipeRepository.getFeaturedRecipe(token)

    fun getToken() = profileRepository.getToken().asLiveData()

    fun getProfile(token: String) = profileRepository.getProfile(token)
}