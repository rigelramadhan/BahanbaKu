package com.bangkit.bahanbaku.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import com.bangkit.bahanbaku.di.AppModule

class MainViewModel(private val repository: RecipeRepository) : ViewModel() {
    fun getRecipes() = repository.getRecipes()

    fun getFeaturedRecipe() = repository.getFeaturedRecipe()

    class MainViewModelFactory private constructor(private val recipeRepository: RecipeRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(recipeRepository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }

        companion object {
            @Volatile
            private var instance: MainViewModelFactory? = null

            fun getInstance(context: Context): MainViewModelFactory =
                instance ?: synchronized(this) {
                instance ?: MainViewModelFactory(AppModule.provideRecipeRepository(context))
                }.also { instance = it }
        }
    }
}