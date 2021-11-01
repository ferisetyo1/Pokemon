package com.feri.pokemon.data.remote.response

import com.google.gson.annotations.SerializedName

data class EvolvesToItem(

	@field:SerializedName("evolution_details")
	val evolutionDetails: List<EvolutionDetailsItem?>? = null,

	@field:SerializedName("species")
	val species: Species? = null,

	@field:SerializedName("evolves_to")
	val evolvesTo: List<EvolvesToItem>? = null,

	@field:SerializedName("is_baby")
	val isBaby: Boolean? = null
)