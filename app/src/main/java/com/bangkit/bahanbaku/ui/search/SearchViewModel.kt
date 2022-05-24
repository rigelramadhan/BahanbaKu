package com.bangkit.bahanbaku.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import com.bangkit.bahanbaku.di.DatabaseModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {
    fun searchRecipe(token: String, search: String) = recipeRepository.searchRecipe(token, search)

    fun getToken(): LiveData<String> {
        return userPreferences.getToken().asLiveData()
    }
}