package com.movie.moviesdetails.models.movies

data class MoviesResponse(
    val page: Int,
    val results: List<MovieItem> ?= null,
    val total_pages: Int,
    val total_results: Int
)