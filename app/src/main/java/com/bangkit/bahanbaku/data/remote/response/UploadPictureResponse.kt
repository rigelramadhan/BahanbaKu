package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class UploadPictureResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: UploadPictureResults
)

data class UploadPictureResults(

	@field:SerializedName("id")
	val id: String
)
