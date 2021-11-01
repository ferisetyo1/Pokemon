package com.feri.pokemon.data.remote

import com.feri.pokemon.data.remote.response.ChainEvolve
import com.feri.pokemon.data.remote.response.Pokemon
import com.feri.pokemon.data.remote.response.PokemonList
import com.feri.pokemon.data.remote.response.PokemonSpecies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: String
    ): Pokemon

    @GET("evolution-chain/{id}")
    suspend fun getPokemonChainEvolve(
        @Path("id") id: String
    ): ChainEvolve

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(
        @Path("id") id: String
    ): PokemonSpecies
}