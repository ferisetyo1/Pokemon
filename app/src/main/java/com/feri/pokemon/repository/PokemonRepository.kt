package com.feri.pokemon.repository

import com.feri.pokemon.data.remote.PokemonApi
import com.feri.pokemon.data.remote.response.ChainEvolve
import com.feri.pokemon.data.remote.response.Pokemon
import com.feri.pokemon.data.remote.response.PokemonList
import com.feri.pokemon.data.remote.response.PokemonSpecies
import com.feri.pokemon.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokemonApi
){
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch(e: Exception) {
            return Resource.Error("Terjadi kesalahan internal")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonDetail(pokemonID: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonDetail(pokemonID)
        } catch(e: Exception) {
            return Resource.Error("Terjadi kesalahan internal")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonChainEvolve(pokemonID: String): Resource<ChainEvolve> {
        val response = try {
            api.getPokemonChainEvolve(pokemonID)
        } catch(e: Exception) {
            return Resource.Error("Terjadi kesalahan internal")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonSpecies(pokemonID: String): Resource<PokemonSpecies> {
        val response = try {
            api.getPokemonSpecies(pokemonID)
        } catch(e: Exception) {
            return Resource.Error("Terjadi kesalahan internal")
        }
        return Resource.Success(response)
    }
}