package com.feri.pokemon.utils

object Constant {
    object Route {
        val splashScreen = "splashScreen"
        val pokemonListScreen = "pokemonListScreen"
        val pokemonDetailScreen = "pokemonDetailScreen"
    }

    fun getPokemonImg(id: String) =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

    fun getLastNumber(url: String?) = if (url.orEmpty().endsWith("/")) {
        url.orEmpty().dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        url.orEmpty().takeLastWhile { it.isDigit() }
    }
}