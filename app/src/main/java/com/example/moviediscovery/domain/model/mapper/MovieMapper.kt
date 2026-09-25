package com.example.moviediscovery.domain.model.mapper

import com.example.moviediscovery.data.dto.MovieDetailsDto
import com.example.moviediscovery.domain.model.MovieDetails

fun MovieDetailsDto.toDomain(): MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        overview = overview,
        posterPath = poster_path,
        backdropPath = backdrop_path,
        releaseDate = release_date,
        voteAverage = vote_average,
        runtime = runtime,
        genres = genres.map { it.name }
    )
}