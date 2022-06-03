package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: ProfileEntity
)

data class ProfileEntity(

	@field:SerializedName("bookmarks")
	val bookmarks: List<String>,

	@field:SerializedName("shipping")
	val shipping: List<ShippingItem>,

	@field:SerializedName("origin")
	val origin: Origin,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("picture")
	val picture: String,

	@field:SerializedName("username")
	val username: String
)

data class ShippingItem(

	@field:SerializedName("cost")
	val cost: Int,

	@field:SerializedName("distance")
	val distance: Int,

	@field:SerializedName("id")
	val id: String
)

data class Origin(

	@field:SerializedName("lng")
	val lng: Double,

	@field:SerializedName("lat")
	val lat: Double
)
