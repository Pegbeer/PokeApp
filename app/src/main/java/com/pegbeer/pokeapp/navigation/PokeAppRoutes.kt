package com.pegbeer.pokeapp.navigation

import androidx.compose.runtime.Composable
import com.pegbeer.pokeapp.ui.home.HomeScreen

interface PokeAppRoutes{
    val route:String
    val screen: @Composable () -> Unit
}

object Home : PokeAppRoutes{
    override val route: String = "home"
    override val screen: @Composable () -> Unit = {  }
}

val pokeAppScreens = listOf(Home)