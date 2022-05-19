package com.bangkit.bahanbaku.data.remote.retrofit

import com.bangkit.bahanbaku.data.remote.response.*
import retrofit2.http.*

interface ApiService {
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @POST("user/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("user/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): ProfileResponse

    @PUT("user/edit")
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): EditProfileResponse

    @PUT("user/edit-password")
    suspend fun editProfilePassword(
        @Header("Authorization") token: String,
        @Field("current-password") currentPassword: String,
        @Field("new-password") newPassword: String,
        @Field("new-password-confirmation") newPasswordConfirmation: String
    ): EditProfileResponse

    @PUT("user/update-location")
    suspend fun updateLocation(
        @Header("Authorization") token: String,
        @Field("lon") lon: Double,
        @Field("lat") lat: Double
    ): UpdateLocationResponse

    // TODO: UPLOAD PICTURE ===> (NOT YET DECIDED)

    // TODO: DELETE USER ===> (ADMIN)

    @GET("/recipe")
    suspend fun getRecipe(
        @Header("Authorization") token: String,
        @Query("search") search: String? = null,
        @Query("featured") featured: Int = 0,
        @Query("new") new: Int? = null
    ): RecipeResponse

    @GET("/recipe/{id}")
    suspend fun getRecipeById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): RecipeResponse

    // TODO: CREATE RECIPE ===> (ADMIN)

    // TODO: UPDATE RECIPE ===> (ADMIN)

    // TODO: DELETE RECIPE ===> (ADMIN)

    @GET("/ingredient")
    suspend fun getIngredient(
        @Header("Authorization") token: String,
        @Query("search") search: String
    ) // TODO: CREATE RESPONSE OBJECT ===> (NOT YET AVAILABLE)

    @GET("/supplier/{id}")
    suspend fun getSupplierById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): SupplierResponse
}