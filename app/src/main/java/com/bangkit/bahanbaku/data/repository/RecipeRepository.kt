package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.local.room.RecipeDatabase
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.utils.Result

class RecipeRepository private constructor(private val apiService: ApiService, private val database: RecipeDatabase) {
    fun getNewRecipes(token: String): LiveData<Result<List<RecipeEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getRecipe(token = token, new = 1)
            val recipes = response.results
            emit(Result.Success(recipes))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getFeaturedRecipe(token: String): LiveData<Result<RecipeEntity>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getRecipe(token = token, featured = 1)
            val recipe = response.results.first()
            emit(Result.Success(recipe))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun searchRecipe(token: String, query: String): LiveData<Result<List<RecipeEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getRecipe(token = token, search = query)
            val recipes = response.results
            emit(Result.Success(recipes))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: RecipeRepository? = null

        fun getInstance(apiService: ApiService, database: RecipeDatabase): RecipeRepository =
            instance ?: synchronized(this) {
                instance ?: RecipeRepository(apiService, database)
            }.also { instance = it }
    }
}