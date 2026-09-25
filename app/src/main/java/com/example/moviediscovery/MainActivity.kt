package com.example.moviediscovery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.moviediscovery.navigation.AppNavigation
import com.example.moviediscovery.presentation.homeScreen.HomeScreen
import com.example.moviediscovery.ui.theme.MovieDiscoveryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieDiscoveryTheme {
                AppNavigation()
            }
        }
    }
}

