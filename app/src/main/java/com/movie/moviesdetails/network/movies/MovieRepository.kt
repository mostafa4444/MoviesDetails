package com.movie.moviesdetails.network.movies

import com.movie.moviesdetails.models.movies.MoviesResponse
import com.movie.moviesdetails.utils.NetworkConstants
import okhttp3.ResponseBody
import retrofit2.Response


interface MovieRepository {
    suspend fun getAllMovies(): Response<MoviesResponse>

}