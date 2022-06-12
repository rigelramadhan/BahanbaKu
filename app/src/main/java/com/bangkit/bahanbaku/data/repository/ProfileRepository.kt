package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.remote.response.*
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class ProfileRepository @Inject constructor(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
//    private val database: ProfileDatabase
) : CoroutineScope {
    fun saveToken(token: String) {
        launch(Dispatchers.IO) {
            userPreferences.saveToken(token)
        }
    }

    fun getToken() = userPreferences.getToken().asLiveData()

    fun deleteToken() {
        launch(Dispatchers.IO) {
            userPreferences.deleteToken()
        }
    }

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

    fun updateUser(
        token: String,
        username: String,
        email: String,
        password: String
    ): LiveData<Result<UpdateProfileResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateProfile(token, username, email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun uploadPicture(
        token: String,
        file: File
    ): LiveData<Result<UploadPictureResponse>> = liveData {
        emit(Result.Loading)

        val mediaType = "image".toMediaTypeOrNull()
        val multipartBody =
            MultipartBody.Part.createFormData("image", file.name, file.asRequestBody(mediaType))

        try {
            val response = apiService.uploadPicture(token, multipartBody)
            emit(Result.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }

    fun updateLocation(
        token: String,
        lon: Double,
        lat: Double
    ): LiveData<Result<UpdateLocationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val location = JSONObject()
            location.put("lat", lat)
            location.put("lng", lon)

            val bodyObject = JSONObject()
            bodyObject.put("location", location)

            val requestBody = bodyObject.toString()
                .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

            val response = apiService.updateLocation(token, requestBody)
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
                recipeList.add(apiService.getRecipeById(token, id).results)
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

    fun deleteBookmarkByPosition(
        token: String,
        position: Int
    ): LiveData<Result<DeleteBookmarkResponse>> = liveData {
        emit(Result.Loading)
        try {
            val bookmarkId = apiService.getProfile(token).results.bookmarks[position]
            val result = apiService.deleteBookmark(token, bookmarkId)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun deleteBookmark(token: String, id: String): LiveData<Result<DeleteBookmarkResponse>> =
        liveData {
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
        var isBookmarked = false
        if (!bookmarks.isNullOrEmpty()) {
            isBookmarked = (id in bookmarks)
        }
        emit(isBookmarked)
    }

    fun isFirstTime() = userPreferences.isFirstTime().asLiveData()

    fun setFirstTime(firstTime: Boolean) {
        launch(Dispatchers.IO) {
            userPreferences.setFirstTime(firstTime)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}