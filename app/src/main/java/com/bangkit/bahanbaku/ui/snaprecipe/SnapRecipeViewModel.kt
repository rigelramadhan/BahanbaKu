package com.bangkit.bahanbaku.ui.snaprecipe

import androidx.lifecycle.ViewModel
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SnapRecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    fun getToken() = profileRepository.getToken()

    fun getRecipes(token: String, foodName: String) = recipeRepository.searchRecipe(token, foodName)
}