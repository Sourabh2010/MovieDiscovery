package com.example.moviediscovery.domain.repository

import com.example.moviediscovery.domain.model.Movie
import com.example.moviediscovery.domain.model.MovieDetails
import com.example.moviediscovery.domain.util.Result

interface MoviesRepo {

    suspend fun getPopularMovies(): Result<List<Movie>>

    suspend fun getTrendingMovies(): Result<List<Movie>>

    suspend fun getTopRatedMovies(): Result<List<Movie>>

    suspend fun searchMovies(query: String): Result<List<Movie>>

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetails>


}