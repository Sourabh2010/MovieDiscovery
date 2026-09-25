package com.example.moviediscovery.data.repoImplementation

import android.util.Log
import com.example.moviediscovery.data.remote.MovieApiService
import com.example.moviediscovery.domain.model.Movie
import com.example.moviediscovery.domain.model.MovieDetails
import com.example.moviediscovery.domain.model.mapper.toDomain
import com.example.moviediscovery.domain.repository.MoviesRepo
import com.example.moviediscovery.domain.util.Result

class MoviesRepoImpl(
    private val apiService: MovieApiService
) : MoviesRepo {

    override suspend fun getPopularMovies(): Result<List<Movie>> {
        return try {
            val response = apiService.getPopularMovies()
            Result.Success(response.results.map { it.toDomain() })
        } catch (e: Exception) {
            Log.e("MoviesRepoImpl", "getPopularMovies: ", e)
            Result.Failure(e.message ?: "Unknown Error")

        }
    }

    override suspend fun getTrendingMovies(): Result<List<Movie>> {
        return try {
            val response = apiService.getTrendingMovies()
            Result.Success(response.results.map { it.toDomain() })
        } catch (e: Exception) {
            Log.e("MoviesRepoImpl", "getTrendingMovies: ", e)
            Result.Failure(e.message ?: "Unknown Error")
        }
    }

    override suspend fun getTopRatedMovies(): Result<List<Movie>> {
        return try {
            val response = apiService.getTopRatedMovies()
            Result.Success(response.results.map { it.toDomain() })
        } catch (e: Exception) {
            Log.e("MoviesRepoImpl", "getTopRatedMovies: ", e)
            Result.Failure(e.message ?: "Unknown Error")
        }
    }

    override suspend fun searchMovies(query: String): Result<List<Movie>> {
       return try {
           val response = apiService.searchMovies(query)
           Result.Success(response.results.map { it.toDomain() })
       }catch (e:Exception){
           Log.e("MoviesRepoImpl", "searchMovies: ", e)
           Result.Failure(e.message?:"Unknown Error")
       }
    }

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetails> {
        return try {
            val response = apiService.getMovieDetails(movieId)
            Result.Success(response.toDomain())
        } catch (e: Exception) {
            Log.e("MoviesRepoImpl", "getMovieDetails: ", e)
            Result.Failure(e.message ?: "Unknown Error")
        }
    }

}
