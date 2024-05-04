package com.pegbeer.pokeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PokeAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = Home.route){
        composable(Home.route){
            Home.screen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }