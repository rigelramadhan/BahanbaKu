package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,
//
//	@field:SerializedName("results")
//	val results: RegisterResults
)

data class RegisterResults(
	val any: Any? = null
)
