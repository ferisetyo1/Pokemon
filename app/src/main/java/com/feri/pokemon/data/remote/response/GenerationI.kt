package com.feri.pokemon.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenerationI(

	@field:SerializedName("yellow")
	val yellow: Yellow? = null,

	@field:SerializedName("red-blue")
	val redBlue: RedBlue? = null
)