package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class SupplierResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val result: SupplierEntity
)

data class ProductItem(

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String
)

data class SupplierEntity(

	@field:SerializedName("product")
	val product: List<ProductItem>,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("origin")
	val origin: List<Double>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("address-obj")
	val addressObj: AddressObj
)

data class AddressObj(

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("district")
	val district: String,

	@field:SerializedName("sub-district")
	val subDistrict: String,

	@field:SerializedName("zip-code")
	val zipCode: String
)
