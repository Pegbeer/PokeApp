@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pegbeer.pokeapp.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.pegbeer.pokeapp.ui.detail.DetailScreen
import com.pegbeer.pokeapp.ui.home.HomeScreen
import com.pegbeer.pokeapp.ui.home.HomeViewModel

interface PokeAppRoutes{
    val route:String
    val screen: @Composable (NavController, SharedTransitionScope, AnimatedContentScope,HomeViewModel) -> Unit
}

object Home : PokeAppRoutes{
    override val route: String = "home"
    override val screen: @Composable (NavController, SharedTransitionScope, AnimatedContentScope,HomeViewModel) -> Unit =
        { navController, sharedTransitionScope, animatedContentScope,viewModel ->
            HomeScreen(
                navController = navController,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
                viewModel = viewModel
            )
        }

}

object Details : PokeAppRoutes{
    override val route: String = "details"
    override val screen: @Composable (NavController, SharedTransitionScope, AnimatedContentScope,HomeViewModel) -> Unit =
        { navController, sharedTransitionScope, animatedContentScope,viewModel ->
            DetailScreen(
                navHostController = navController,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
                viewModel = viewModel
            )
        }
}

val pokeAppScreens = listOf(Home,Details)