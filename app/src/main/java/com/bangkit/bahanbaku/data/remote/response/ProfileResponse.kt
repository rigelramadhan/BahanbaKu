package com.bangkit.bahanbaku.data.remote.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: ProfileEntity
)

data class ShippingItem(

	@field:SerializedName("cost")
	val cost: Int,

	@field:SerializedName("id")
	val id: String
)

@Entity(tableName = "profile")
data class ProfileEntity(

	@field:SerializedName("bookmarks")
	val bookmarks: List<String>,

	@field:SerializedName("shipping")
	val shipping: List<ShippingItem>,

	@field:SerializedName("origin")
	val origin: List<Double>,

	@PrimaryKey
	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("picture")
	val picture: String,

	@field:SerializedName("username")
	val username: String
)
