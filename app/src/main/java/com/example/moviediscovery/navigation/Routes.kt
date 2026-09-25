package com.example.moviediscovery.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {


    @Serializable
    data object HomeScreen : Routes()


    @Serializable
    data class MovieDetailScreen(val movieId: Int) : Routes()


    @Serializable
    data object SearchScreen : Routes()

}