package com.bangkit.bahanbaku.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import com.bangkit.bahanbaku.di.AppModule

class HomeViewModel(
    private val recipeRepository: RecipeRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {
    fun getRecipes(token: String) = recipeRepository.getNewRecipes(token)

    fun getFeaturedRecipe(token: String) = recipeRepository.getFeaturedRecipe(token)

    fun getProfile(token: String) = profileRepository.getProfile(token)

    class HomeViewModelFactory private constructor(
        private val recipeRepository: RecipeRepository,
        private val profileRepository: ProfileRepository
    ) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(recipeRepository, profileRepository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }

        companion object {
            @Volatile
            private var instance: HomeViewModelFactory? = null

            fun getInstance(context: Context): HomeViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: HomeViewModelFactory(
                        AppModule.provideRecipeRepository(context),
                        AppModule.provideProfileRepository(context)
                    )
                }.also { instance = it }
        }
    }
}