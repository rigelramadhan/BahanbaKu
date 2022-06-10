package com.bangkit.bahanbaku.ui.detail

import androidx.lifecycle.ViewModel
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {
    fun getRecipe(token: String, id: String) = recipeRepository.getRecipeById(token, id)

    fun checkIfRecipeBookmarked(token: String, id: String) = profileRepository.checkIfRecipeBookmarked(token, id)

    fun addBookmark(token: String, id: String) = profileRepository.addBookmark(token, id)

    fun deleteBookmark(token: String, id: String) = profileRepository.deleteBookmark(token, id)

    fun getToken() = profileRepository.getToken()

    fun getProfile(token: String) = profileRepository.getProfile(token)
}