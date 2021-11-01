package com.feri.pokemon.data.remote.response

import com.google.gson.annotations.SerializedName

data class MoveLearnMethod(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)