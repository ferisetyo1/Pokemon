package com.feri.pokemon.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenerationVii(

	@field:SerializedName("icons")
	val icons: Icons? = null,

	@field:SerializedName("ultra-sun-ultra-moon")
	val ultraSunUltraMoon: UltraSunUltraMoon? = null
)