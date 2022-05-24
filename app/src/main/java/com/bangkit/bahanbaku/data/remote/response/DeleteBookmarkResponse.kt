package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class DeleteBookmarkResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: DeleteBookmarkResults
)

data class DeleteBookmarkResults(
	val any: Any? = null
)
