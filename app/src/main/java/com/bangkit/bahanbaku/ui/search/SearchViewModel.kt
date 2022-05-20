package com.bangkit.bahanbaku.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import com.bangkit.bahanbaku.di.AppModule

class SearchViewModel private constructor(
    private val recipeRepository: RecipeRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {
    fun searchRecipe(token: String, search: String) = recipeRepository.searchRecipe(token, search)

    fun getToken(): LiveData<String> {
        return userPreferences.getToken().asLiveData()
    }

    class SearchViewModelFactory private constructor(
        private val recipeRepository: RecipeRepository,
        private val userPreferences: UserPreferences
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                return SearchViewModel(recipeRepository, userPreferences) as T
            }

            throw IllegalArgumentException("Unknown viewModel class ${modelClass.name}")
        }

        companion object {
            @Volatile
            private var instance: SearchViewModelFactory? = null

            fun getInstance(context: Context): SearchViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: SearchViewModelFactory(
                        AppModule.provideRecipeRepository(context),
                        AppModule.provideUserPreferences(context)
                    )
                }.also { instance = it }
        }
    }
}