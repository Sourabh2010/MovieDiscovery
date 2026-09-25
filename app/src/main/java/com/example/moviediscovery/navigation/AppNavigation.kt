package com.example.moviediscovery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.moviediscovery.presentation.homeScreen.HomeScreen
import com.example.moviediscovery.presentation.homeScreen.MovieDetailScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.HomeScreen){
        composable<Routes.HomeScreen> {
            HomeScreen(navHostController = navController)
        }

        composable<Routes.MovieDetailScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.MovieDetailScreen>() // it convert to object
            MovieDetailScreen(
                navController = navController,
                movieId = args.movieId
            )
        }


    }
}