package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.local.room.ProfileDatabase
import com.bangkit.bahanbaku.data.remote.response.ProfileEntity
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.utils.Result

class ProfileRepository private constructor(
    private val apiService: ApiService,
    private val database: ProfileDatabase
) {

    fun getProfile(): LiveData<Result<ProfileEntity>> = liveData {
        emit(Result.Loading)
        try {
            val profile = apiService.getProfile().profileEntity
            emit(Result.Success(profile))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getBookmarks(): LiveData<Result<List<RecipeEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val profile = apiService.getProfile().profileEntity
            val recipeList = mutableListOf<RecipeEntity>()
            for (id in profile.bookmark) {
                recipeList.add(apiService.getRecipe(id).result.first())
            }

            emit(Result.Success(recipeList))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: ProfileRepository? = null

        fun getInstance(apiService: ApiService, database: ProfileDatabase): ProfileRepository =
            instance ?: synchronized(this) {
                instance ?: ProfileRepository(apiService, database)
            }.also { instance = it }
    }
}