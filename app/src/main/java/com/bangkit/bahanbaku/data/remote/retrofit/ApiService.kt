package com.bangkit.bahanbaku.data.remote.retrofit

import com.bangkit.bahanbaku.data.remote.response.*
import okhttp3.MultipartBody
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
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("new-password") newPassword: String
    ): UpdateProfileResponse

    @PUT("user/update-location")
    suspend fun updateLocation(
        @Header("Authorization") token: String,
        @Field("location") location: List<Double>
    ): UpdateLocationResponse

    @POST("user/upload-picture")
    suspend fun uploadPicture(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): UploadPictureResponse

    @POST("user/bookmarks/{id}")
    suspend fun addBookmark(
        @Path("id") id: String
    ): AddBookmarkResponse

    @DELETE("user/bookmarks/{id}")
    suspend fun deleteBookmark(
        @Path("id") id: String
    ): DeleteBookmarkResponse

    // TODO: DELETE USER ===> (ADMIN)

    @GET("/recipe")
    suspend fun getRecipe(
        @Header("Authorization") token: String,
        @Query("search") search: String? = null,
        @Query("featured") featured: Int? = null,
        @Query("new") new: Int? = null
    ): RecipeResponse

    @GET("/recipe/{id}")
    suspend fun getRecipeById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): RecipeByIdResponse

    // TODO: CREATE RECIPE ===> (ADMIN)

    // TODO: UPDATE RECIPE ===> (ADMIN)

    // TODO: DELETE RECIPE ===> (ADMIN)

    @GET("/ingredient")
    suspend fun getIngredient(
        @Header("Authorization") token: String,
        @Query("search") search: String
    ) // TODO: CREATE RESPONSE OBJECT ===> (NOT YET AVAILABLE)

    @GET("/supplier")
    suspend fun getSupplier(
        @Header("Authorization") token: String
    ): SupplierResponse

    @GET("/supplier/{id}")
    suspend fun getSupplierById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): SupplierByIdResponse
}