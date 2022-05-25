package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class SupplierByIdResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: SupplierByIdResults
)

data class SupplierByIdResults(

	@field:SerializedName("supplier")
	val supplier: SupplierEntity
)
