package com.example.moviediscovery.data.remote

import com.example.moviediscovery.data.dto.MovieDetailsDto
import com.example.moviediscovery.data.dto.MoviesResponseDto
import com.example.moviediscovery.di.DataModule.ApiKey
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter

class MovieApiService(private val httpClient: HttpClient) {

    suspend fun getPopularMovies(): MoviesResponseDto {
        return httpClient.get("/3/movie/popular") {
            headers {
                append("Authorization", "Bearer $ApiKey")
                append("accept", "application/json")
            }
        }.body()
    }

    suspend fun getTrendingMovies(): MoviesResponseDto {
        return httpClient.get("/3/trending/movie/day") {
            headers {
                append("Authorization", "Bearer $ApiKey")
                append("accept", "application/json")
            }
        }.body()
    }

    suspend fun getTopRatedMovies(): MoviesResponseDto {
        return httpClient.get("/3/movie/top_rated") {
            headers {
                append("Authorization", "Bearer $ApiKey")
                append("accept", "application/json")
            }
        }.body()
    }

    suspend fun searchMovies(query: String): MoviesResponseDto {
        return httpClient.get("/search/movie") {
            parameter("query", query)
            parameter("include_adult", false)
            parameter("language", "en-US")
            headers {
                append("Authorization", "Bearer $ApiKey")
                append("accept", "application/json")
            }
        }.body()
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetailsDto {
        return httpClient.get("/3/movie/$movieId") {

            parameter("language", "en-US")
            headers {
                append("Authorization", "Bearer $ApiKey")
                append("accept", "application/json")
            }
        }.body()
    }


}
