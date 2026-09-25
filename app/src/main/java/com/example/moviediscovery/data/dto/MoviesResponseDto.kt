package com.example.moviediscovery.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class MoviesResponseDto(
    val page: Int,
    val results: List<MoviesDto>,
    val total_pages: Int,
    val total_results: Int
)
