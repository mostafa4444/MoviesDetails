package com.movie.moviesdetails.network.movies

import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val apiServices: MovieServices
) : MovieRepository {
}