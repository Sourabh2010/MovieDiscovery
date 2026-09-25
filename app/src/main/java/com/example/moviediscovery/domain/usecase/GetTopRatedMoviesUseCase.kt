package com.example.moviediscovery.domain.usecase

import com.example.moviediscovery.domain.model.Movie
import com.example.moviediscovery.domain.repository.MoviesRepo
import com.example.moviediscovery.domain.util.Result
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val repository: MoviesRepo) {
    suspend operator fun invoke(): Result<List<Movie>>{
        return repository.getTopRatedMovies()
    }
}