package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class SupplierResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: SupplierResult
)

data class SupplierResult(

	@field:SerializedName("suppliers")
	val suppliers: List<SupplierEntity>
)

data class AddressObj(

	@field:SerializedName("zipCode")
	val zipCode: String,

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("district")
	val district: String,

	@field:SerializedName("subDistrict")
	val subDistrict: String
)

data class ProductItem(

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String
)

data class SupplierOrigin(

	@field:SerializedName("lng")
	val lng: Double,

	@field:SerializedName("lat")
	val lat: Double
)

data class SupplierEntity(

	@field:SerializedName("product")
	val product: List<ProductItem>,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("origin")
	val origin: SupplierOrigin,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("addressObj")
	val addressObj: AddressObj
)
