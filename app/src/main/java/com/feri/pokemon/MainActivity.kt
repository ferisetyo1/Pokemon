package com.feri.pokemon

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.feri.pokemon.feature.pokemondetail.component.PokemonDetailScreen
import com.feri.pokemon.feature.pokemonlist.component.PokemonListScreen
import com.feri.pokemon.ui.theme.PokemonTheme
import com.feri.pokemon.utils.Constant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                val navController = rememberNavController()
                initNavigator(navController)
            }
        }
    }

    @Composable
    private fun initNavigator(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Constant.Route.splashScreen
        ) {
            composable(Constant.Route.splashScreen) {
                splashScreen(navController = navController)
            }
            composable(Constant.Route.pokemonListScreen) {
                navController.enableOnBackPressed(false)
                PokemonListScreen(navController = navController)
            }
            composable(
                "${Constant.Route.pokemonDetailScreen}/{color}/{pokemonName}/{pokemonID}",
                arguments = listOf(
                    navArgument("color") {
                        type = NavType.StringType
                    },
                    navArgument("pokemonName") {
                        type = NavType.StringType
                    },
                    navArgument("pokemonID") {
                        type = NavType.StringType
                    }
                )
            ) {
                navController.enableOnBackPressed(true)
                val color = remember {
                    it.arguments?.getString("color")
                }
                val pokemonName = remember {
                    it.arguments?.getString("pokemonName")
                }
                val pokemonID = remember {
                    it.arguments?.getString("pokemonID")
                }
                PokemonDetailScreen(
                    color = color?.toIntOrNull() ?: 0,
                    pokemonName = pokemonName?.lowercase(Locale.ROOT) ?: "",
                    pokemonID = pokemonID.orEmpty(),
                    navController = navController
                )
            }
        }
    }

    @Composable
    fun splashScreen(navController: NavHostController) {
        val scale = remember {
            Animatable(1f)
        }
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 2.5f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )
            delay(2000)
            navController.navigate(Constant.Route.pokemonListScreen) {

            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.scale(scale.value),horizontalAlignment = Alignment.End){
                Text(
                    text = "Pokemon Collection",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.W700,
                    fontSize = 16.sp,
                    modifier = Modifier.background(
                        brush = Brush.linearGradient(listOf(Color.LightGray, Color.DarkGray))
                    )
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "by Feri",
                    fontSize = 10.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.W700
                )
            }
        }
    }

}

