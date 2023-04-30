package com.movie.moviesdetails.ui.movies.fragments.movieDetails

import android.provider.ContactsContract.Data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.moviesdetails.base.BaseViewModel
import com.movie.moviesdetails.models.movies.MovieFilterResponse
import com.movie.moviesdetails.models.movies.MoviesResponse
import com.movie.moviesdetails.models.network.DataState
import com.movie.moviesdetails.models.network.ProgressBarState
import com.movie.moviesdetails.usecases.movies.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase
) : BaseViewModel(){

    private val _movieState: MutableStateFlow<DataState<MovieFilterResponse>> = MutableStateFlow(DataState.Loading(ProgressBarState.Idle))
    val movieState: StateFlow<DataState<MovieFilterResponse>> = _movieState


    fun getMovieDetailById(movieID: Int){
        movieDetailUseCase.execute(movieID).onEach {dataState ->
            when(dataState){
                is DataState.Success ->{
                    _movieState.value  = dataState.copy(dataState.data)
                }
                is DataState.Error ->{
                    _movieState.value  = dataState.copy(dataState.exception)
                }
                is DataState.Loading ->{
                    _movieState.value  = dataState.copy(dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

}