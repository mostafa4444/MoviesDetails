package com.movie.moviesdetails.network.movies

import com.movie.moviesdetails.models.movies.MoviesResponse
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val apiServices: MovieServices
) : MovieRepository {
    override suspend fun getAllMovies(): Response<MoviesResponse> {
        return apiServices.getAllMovies()
    }
}