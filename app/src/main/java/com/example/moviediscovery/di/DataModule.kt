package com.example.moviediscovery.di

import com.example.moviediscovery.data.remote.MovieApiService
import com.example.moviediscovery.data.repoImplementation.MoviesRepoImpl
import com.example.moviediscovery.domain.repository.MoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            defaultRequest {
                url(base_url)
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            install(Logging) {
                level = LogLevel.BODY
            }
        }
    }

    @Provides
    @Singleton
    fun provideMovieApiService(httpClient: HttpClient): MovieApiService {
        return MovieApiService(httpClient)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieApiService: MovieApiService): MoviesRepo {
        return MoviesRepoImpl(movieApiService)
    }



     val base_url = "https://api.themoviedb.org"
     val ApiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYWFhOTI3ZWUwYWJlZDRlMzlkZTYxZjJmMDc5OGVhZiIsIm5iZiI6MTc2ODY0NzQzMi44ODUsInN1YiI6IjY5NmI2YjA4ZDE5MDk2NDc1NTY4OTU1OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.BXG_42LhOTJht-plc3dwe08tphyi8Hpm4-AMSqYPIWI"

}