package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.remote.response.SnapFoodResponse
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.data.remote.retrofit.ApiServiceML
import com.bangkit.bahanbaku.utils.Result
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val apiService: ApiServiceML,
//    private val database: FoodDatabase
) {
    fun postSnapFood(token: String, file: File): LiveData<Result<SnapFoodResponse>> = liveData {
        emit(Result.Loading)

        val imageMediaType = "image/jpeg".toMediaTypeOrNull()
        val imageMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo",
            file.name,
            file.asRequestBody(imageMediaType)
        )

        try {
            val response = apiService.uploadSnapFood(token, imageMultiPart)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}