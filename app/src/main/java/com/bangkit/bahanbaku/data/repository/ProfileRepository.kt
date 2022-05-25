package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.local.room.ProfileDatabase
import com.bangkit.bahanbaku.data.remote.response.*
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.utils.Result
import javax.inject.Inject

class ProfileRepository @Inject constructor(
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
            val response = apiService.updateLocation(token, listOf(lon, lat))
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getBookmarks(token: String): LiveData<Result<List<RecipeEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val profile = apiService.getProfile(token).results
            val recipeList = mutableListOf<RecipeEntity>()
            for (id in profile.bookmarks) {
                recipeList.add(apiService.getRecipeById(token, id).results.recipe)
            }
            emit(Result.Success(recipeList))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun addBookmark(token: String, id: String): LiveData<Result<AddBookmarkResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.addBookmark(token, id)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun deleteBookmarkByPosition(token: String, position: Int): LiveData<Result<DeleteBookmarkResponse>> = liveData {
        emit(Result.Loading)
        try {
            val bookmarkId = apiService.getProfile(token).results.bookmarks[position]
            val result = apiService.deleteBookmark(token, bookmarkId)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun deleteBookmark(token: String, id: String): LiveData<Result<DeleteBookmarkResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.deleteBookmark(token, id)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun checkIfRecipeBookmarked(token: String, id: String): LiveData<Boolean> = liveData {
        val bookmarks = apiService.getProfile(token).results.bookmarks
        emit(id in bookmarks)
    }
}