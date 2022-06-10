package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class NearbyRestoResponse(

	@field:SerializedName("status")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: List<RestoEntity>
)

data class Location(

	@field:SerializedName("lng")
	val lng: Double,

	@field:SerializedName("lat")
	val lat: Double
)

data class RestoEntity(

	@field:SerializedName("user_ratings_total")
	val userRatingsTotal: Int,

	@field:SerializedName("restaurantName")
	val restaurantName: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("location")
	val location: Location
)
