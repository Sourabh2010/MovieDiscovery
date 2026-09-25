package com.example.moviediscovery.domain.usecase

import com.example.moviediscovery.domain.model.Movie
import com.example.moviediscovery.domain.repository.MoviesRepo
import com.example.moviediscovery.domain.util.Result
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(private val repository: MoviesRepo) {
    suspend operator fun invoke(query: String):Result<List<Movie>> {
        return repository.searchMovies(query)
    }
}