package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class NearbyRestoResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: NearbyResults
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
	val rating: Int,

	@field:SerializedName("location")
	val location: Location
)

data class NearbyResults(

	@field:SerializedName("resto")
	val resto: List<RestoEntity>
)
