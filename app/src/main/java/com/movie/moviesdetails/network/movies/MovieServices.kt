package com.movie.moviesdetails.network.movies

import com.movie.moviesdetails.models.movies.MovieFilterResponse
import com.movie.moviesdetails.models.movies.MoviesResponse
import com.movie.moviesdetails.utils.NetworkConstants.API_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServices {

    @GET("discover/movie")
    suspend fun getAllMovies(
        @Query("api_key") apiKey: String ?= API_KEY
    ): Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String ?= API_KEY
    ): Response<MovieFilterResponse>

}