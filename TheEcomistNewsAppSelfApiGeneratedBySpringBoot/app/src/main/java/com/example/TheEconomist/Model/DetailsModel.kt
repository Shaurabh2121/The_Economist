package com.example.TheEconomist.Model

import com.google.gson.annotations.SerializedName

data class DetailsModel(

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("author")
       val author: String? = null,
	@field:SerializedName("description")
	val description: String? = null
)