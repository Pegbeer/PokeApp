package com.pegbeer.pokeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.pegbeer.pokeapp.ui.home.HomeScreen
import com.pegbeer.pokeapp.ui.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokeAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = Home.route){
        composable(Home.route){
            val viewModel:HomeViewModel = koinViewModel()
            val pokemons = viewModel.pokemons.collectAsLazyPagingItems()
            HomeScreen(pokemons = pokemons)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }