package com.example.TheEconomist.SearchModel

import com.google.gson.annotations.SerializedName

data class ResponseSearchDTO(

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("data")
	val data: List<DataSearchDTO?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)