package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.local.room.RecipeDatabase
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.utils.Result

class RecipeRepository private constructor(private val apiService: ApiService, private val database: RecipeDatabase) {
    fun getRecipes(): LiveData<Result<List<RecipeEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getRecipes()
            val recipes = response.result
            emit(Result.Success(recipes))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getFeaturedRecipe(): LiveData<Result<RecipeEntity>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getFeaturedRecipe()
            val recipe = response.recipe
            emit(Result.Success(recipe))
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