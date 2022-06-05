package com.bangkit.bahanbaku.data.remote.retrofit

import com.bangkit.bahanbaku.data.remote.response.SnapFoodResponse
import okhttp3.MultipartBody
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServiceML {

    @Multipart
    @POST("/upload")
    suspend fun uploadSnapFood(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): SnapFoodResponse
}