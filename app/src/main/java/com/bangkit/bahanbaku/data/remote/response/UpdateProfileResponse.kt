package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(

	@field:SerializedName("status")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: UpdateProfileResults
)

data class UpdateProfileResults(

	@field:SerializedName("id")
	val id: String
)
