package com.movie.moviesdetails.usecases.movies

import com.movie.moviesdetails.network.movies.MovieRepositoryImpl
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepositoryImpl
) {
}