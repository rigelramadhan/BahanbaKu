package com.bangkit.bahanbaku.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import com.bangkit.bahanbaku.di.AppModule

class DetailViewModel(
    private val recipeRepository: RecipeRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {
    fun getRecipe(token: String, id: String) = recipeRepository.getRecipeById(token, id)

    class DetailViewModelFactory private constructor(
        private val recipeRepository: RecipeRepository,
        private val userPreferences: UserPreferences
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel(recipeRepository, userPreferences) as T
            }

            throw IllegalArgumentException("Unknown viewModel class ${modelClass.name}")
        }

        companion object {
            @Volatile
            private var instance: DetailViewModelFactory? = null

            fun getInstance(context: Context): DetailViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: DetailViewModelFactory(
                        AppModule.provideRecipeRepository(context),
                        AppModule.provideUserPreferences(context)
                    )
                }.also { instance = it }
        }
    }
}