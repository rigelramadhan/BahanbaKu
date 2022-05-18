package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.local.room.ProfileDatabase
import com.bangkit.bahanbaku.data.remote.response.LoginResponse
import com.bangkit.bahanbaku.data.remote.response.ProfileResponse
import com.bangkit.bahanbaku.data.remote.response.RegisterResponse
import com.bangkit.bahanbaku.data.remote.response.UpdateLocationResponse
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.utils.Result

class ProfileRepository private constructor(
    private val apiService: ApiService,
    private val database: ProfileDatabase
) {

    fun getProfile(token: String): LiveData<Result<ProfileResponse>> = liveData {
        emit(Result.Loading)
        try {
            val profile = apiService.getProfile(token)
            emit(Result.Success(profile))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun register(
        username: String,
        email: String,
        password: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(username, email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun updateLocation(
        token: String,
        lon: Double,
        lat: Double
    ): LiveData<Result<UpdateLocationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateLocation(token, lon, lat)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

//    fun getBookmarks(token: String): LiveData<Result<List<RecipeEntity>>> = liveData {
//        emit(Result.Loading)
//        try {
//            val profile = apiService.getProfile(token)
//            val recipeList = mutableListOf<RecipeEntity>()
//            for (id in profile.bookmark) {
//                recipeList.add(apiService.getRecipe(id).result.first())
//            }
//
//            emit(Result.Success(recipeList))
//        } catch (e: Exception) {
//            emit(Result.Error(e.message.toString()))
//        }
//    }

    companion object {
        @Volatile
        private var instance: ProfileRepository? = null

        fun getInstance(apiService: ApiService, database: ProfileDatabase): ProfileRepository =
            instance ?: synchronized(this) {
                instance ?: ProfileRepository(apiService, database)
            }.also { instance = it }
    }
}