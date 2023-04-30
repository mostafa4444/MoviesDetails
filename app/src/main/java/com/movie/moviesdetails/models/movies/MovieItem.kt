package com.movie.moviesdetails.models.movies

import android.R.id.input
import java.time.LocalDate
import java.time.format.DateTimeFormatter


data class MovieItem(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
){
    fun extractReleaseYear(): String{
        release_date.split("-").let {
            if (it.isNotEmpty()){
                return it.first()
            }
        }
        return ""
    }
}