package com.pegbeer.pokeapp.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pegbeer.pokeapp.ui.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokeAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope
){
    val viewModel:HomeViewModel = koinViewModel()
    NavHost(navController = navController, startDestination = Home.route){
        composable(Home.route){
            Home.screen(navController,sharedTransitionScope,this@composable,viewModel)
        }
        composable(Details.route){
            Details.screen(navController,sharedTransitionScope,this@composable,viewModel)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }