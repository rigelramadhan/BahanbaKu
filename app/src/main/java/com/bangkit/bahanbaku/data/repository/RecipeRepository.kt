package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.local.room.RecipeDatabase
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.utils.Result
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: RecipeDatabase
) {
    fun getNewRecipes(token: String): LiveData<Result<List<RecipeEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getRecipe(token = token, new = 1)
            val recipes = response.results.recipes
            emit(Result.Success(recipes))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getFeaturedRecipe(token: String): LiveData<Result<RecipeEntity>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getRecipe(token = token, featured = 1)
            val recipe = response.results.recipes.first()
            emit(Result.Success(recipe))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun searchRecipe(token: String, query: String): LiveData<Result<List<RecipeEntity>>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getRecipe(token = token, search = query)
                val recipes = response.results.recipes
                emit(Result.Success(recipes))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    fun getRecipeById(token: String, id: String): LiveData<Result<RecipeEntity>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getRecipeById(token, id)
            val recipe = response.results.recipe
            emit(Result.Success(recipe))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}