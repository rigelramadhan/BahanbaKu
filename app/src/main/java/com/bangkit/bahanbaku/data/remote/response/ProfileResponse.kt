package com.bangkit.bahanbaku.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileResponse(

    @field:SerializedName("result")
    val profileEntity: ProfileEntity
) : Parcelable

@Parcelize
data class ProfileEntity(

    @field:SerializedName("passwordChangedAt")
    val passwordChangedAt: String,

    @field:SerializedName("role")
    val role: String,

    @field:SerializedName("origin")
    val origin: Origin,

    @field:SerializedName("photo")
    val photo: String,

    @field:SerializedName("token")
    val token: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("bookmark")
    val bookmark: List<Int>,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("shipping")
    val shipping: List<ShippingItem>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String
) : Parcelable

@Parcelize
data class Origin(
    @field:SerializedName("lat")
    val lat: Double,

    @field:SerializedName("lon")
    val lon: Double
) : Parcelable

@Parcelize
data class ShippingItem(

    @field:SerializedName("shippingCost")
    val shippingCost: Int,

    @field:SerializedName("idToko")
    val idToko: Int
) : Parcelable
