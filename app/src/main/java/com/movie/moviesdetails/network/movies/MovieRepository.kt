package com.movie.moviesdetails.network.movies

import com.movie.moviesdetails.models.movies.MovieFilterResponse
import com.movie.moviesdetails.models.movies.MoviesResponse
import com.movie.moviesdetails.utils.NetworkConstants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieRepository {
    suspend fun getAllMovies(): Response<MoviesResponse>

    suspend fun getMovieById(
        movieId: Int
    ): Response<MovieFilterResponse>


}