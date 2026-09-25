package com.example.moviediscovery.domain.usecase

import com.example.moviediscovery.domain.model.MovieDetails
import com.example.moviediscovery.domain.repository.MoviesRepo
import com.example.moviediscovery.domain.util.Result
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MoviesRepo
) {

    suspend operator fun invoke(
        movieId: Int
    ): Result<MovieDetails> {

        return repository.getMovieDetails(movieId)
    }
}