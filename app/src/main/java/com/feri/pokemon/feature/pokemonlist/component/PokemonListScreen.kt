package com.feri.pokemon.feature.pokemonlist.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.feri.pokemon.feature.pokemonlist.viewmodel.PokemonListViewModel

@Composable
fun PokemonListScreen(
    navController: NavHostController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = "Pokemon Collection by Feri",
                fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(16.dp, 16.dp, 0.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ListSection(navController, viewModel)
        }
    }
}

