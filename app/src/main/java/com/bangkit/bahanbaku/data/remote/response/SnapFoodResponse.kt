package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class SnapFoodResponse(

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(

	@field:SerializedName("image_link")
	val imageLink: String,

	@field:SerializedName("probability")
	val probability: String,

	@field:SerializedName("food")
	val food: String
)
