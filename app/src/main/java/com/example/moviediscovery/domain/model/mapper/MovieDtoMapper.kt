package com.example.moviediscovery.domain.model.mapper

import com.example.moviediscovery.data.dto.MoviesDto
import com.example.moviediscovery.domain.model.Movie

fun MoviesDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = poster_path,
        backdropPath = backdrop_path,
        releaseDate = release_date,
        voteAverage = vote_average
    )
}