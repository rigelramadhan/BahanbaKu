package com.bangkit.bahanbaku.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import com.bangkit.bahanbaku.di.DatabaseModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {
    fun getRecipes(token: String) = repository.getNewRecipes(token)

    fun getFeaturedRecipe(token: String) = repository.getFeaturedRecipe(token)
}