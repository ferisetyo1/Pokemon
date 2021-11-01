package com.feri.pokemon.feature.pokemondetail.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feri.pokemon.data.remote.response.ChainEvolve
import com.feri.pokemon.data.remote.response.Pokemon
import com.feri.pokemon.data.remote.response.PokemonSpecies
import com.feri.pokemon.repository.PokemonRepository
import com.feri.pokemon.utils.Constant
import com.feri.pokemon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    val errorMsg = mutableStateOf("")
    val pokemonStat = mutableStateOf<Pokemon?>(null)
    val pokemonChainEvolve = mutableStateOf<ChainEvolve?>(null)
    val pokemonSpecies = mutableStateOf<PokemonSpecies?>(null)
    val isLoading = mutableStateOf(true)

    fun getPokemonDetail(pokemonID: String) {
        viewModelScope.launch {
            val result = repository.getPokemonDetail(pokemonID)
            isLoading.value = true
            when (result) {
                is Resource.Success -> {
                    isLoading.value = false
                    result.data?.let { pokemon ->
                        pokemonStat.value = pokemon
                    }
                }
                is Resource.Error -> {
                    errorMsg.value = result.message.orEmpty()
                    isLoading.value = false
                }
            }
        }
    }

    fun getPokemonChainEvolve(pokemonID: String) {
        viewModelScope.launch {
            val result = repository.getPokemonChainEvolve(pokemonID)
            isLoading.value = true
            when (result) {
                is Resource.Success -> {
                    isLoading.value = false
                    result.data?.let { chainEvolve ->
                        pokemonChainEvolve.value = chainEvolve
                    }
                }
                is Resource.Error -> {
                    errorMsg.value = result.message.orEmpty()
                    isLoading.value = false
                }
            }
        }
    }

    fun getPokemonSpecies(pokemonID: String) {
        viewModelScope.launch {
            val result = repository.getPokemonSpecies(pokemonID)
            isLoading.value = true
            when (result) {
                is Resource.Success -> {
                    isLoading.value = false
                    result.data?.let { species ->
                        pokemonSpecies.value = species
                        println("evolve-chain:" + species.evolutionChain)
                        species.evolutionChain?.let {
                            getPokemonChainEvolve(Constant.getLastNumber(it.url))
                        }
                    }
                }
                is Resource.Error -> {
                    errorMsg.value = result.message.orEmpty()
                    isLoading.value = false
                }
            }
        }
    }
}
