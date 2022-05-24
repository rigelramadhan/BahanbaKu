package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: LoginResults
)

data class LoginResults(

	@field:SerializedName("token")
	val token: String
)
