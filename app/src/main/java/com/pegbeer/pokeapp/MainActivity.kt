package com.pegbeer.pokeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.pegbeer.pokeapp.navigation.Home
import com.pegbeer.pokeapp.navigation.PokeAppNavHost
import com.pegbeer.pokeapp.navigation.PokeAppRoutes
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeAppTheme {
                val currentScreen:PokeAppRoutes by remember{ mutableStateOf(Home) }
                val navController = rememberNavController()
                PokeAppNavHost(navController = navController)
            }
        }
    }
}