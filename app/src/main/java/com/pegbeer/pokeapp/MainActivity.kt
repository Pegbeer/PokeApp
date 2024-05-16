package com.pegbeer.pokeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.navigation.compose.rememberNavController
import com.pegbeer.pokeapp.navigation.PokeAppNavHost
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme

@OptIn(ExperimentalSharedTransitionApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeAppTheme {
                SharedTransitionLayout {
                    val navController = rememberNavController()
                    PokeAppNavHost(
                        navController = navController,
                        sharedTransitionScope = this
                    )
                }
            }
        }
    }
}