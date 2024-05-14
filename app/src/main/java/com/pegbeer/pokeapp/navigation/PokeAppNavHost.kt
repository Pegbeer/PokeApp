package com.pegbeer.pokeapp.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.gson.Gson
import com.pegbeer.pokeapp.ui.detail.DetailScreen
import com.pegbeer.pokeapp.ui.home.HomeScreen
import com.pegbeer.pokeapp.ui.home.HomeViewModel
import me.pegbeer.pokeapp.core.model.Pokemon
import me.pegbeer.pokeapp.core.util.parcelable
import me.pegbeer.pokeapp.core.util.serializable
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

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