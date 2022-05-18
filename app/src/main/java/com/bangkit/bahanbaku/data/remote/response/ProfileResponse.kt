package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("shipping")
	val shipping: List<ShippingItem>,

	@field:SerializedName("origin")
	val origin: List<Double>,

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

	@field:SerializedName("gJDNQCOC")
	val gJDNQCOC: Int
)
