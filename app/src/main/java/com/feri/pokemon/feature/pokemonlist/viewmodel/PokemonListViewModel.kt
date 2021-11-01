package com.feri.pokemon.feature.pokemonlist.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.feri.pokemon.data.remote.response.PokemonList
import com.feri.pokemon.repository.PokemonRepository
import com.feri.pokemon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    val errorMsg = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    var isMaxPage = mutableStateOf(false)
    val pokemonList = mutableStateOf<List<PokemonList.ResultsItem>>(listOf())
    val PAGE_SIZE = 20
    var curPage = 0

    init {
        getListPokemon()
    }

    fun getListPokemon() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val result = repository.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE)
                when (result) {
                    is Resource.Success -> {
                        errorMsg.value = ""
                        isLoading.value = false
                        result.data?.let {
                            isMaxPage.value = curPage * PAGE_SIZE >= it.count ?: 0
                            it.results?.let {
                                pokemonList.value += it
                            }
                        }

                        curPage++
                    }
                    is Resource.Error -> {
                        errorMsg.value = result.message.orEmpty()
                        isLoading.value = false
                    }
                }
            }catch (e:Exception){
                Timber.e(e)
            }
        }
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}
