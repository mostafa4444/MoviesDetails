package com.movie.moviesdetails.ui.movies.fragments.movieListing

import androidx.lifecycle.viewModelScope
import com.movie.moviesdetails.base.BaseViewModel
import com.movie.moviesdetails.models.movies.MoviesResponse
import com.movie.moviesdetails.models.network.DataState
import com.movie.moviesdetails.models.network.ProgressBarState
import com.movie.moviesdetails.usecases.movies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel(){


    private val _moviesState: MutableStateFlow<DataState<MoviesResponse>> = MutableStateFlow(DataState.Loading(
        ProgressBarState.Idle))
    val moviesState: StateFlow<DataState<MoviesResponse>> = _moviesState

    init {
        getMovies()
    }
    fun getMovies(){
        getMoviesUseCase.execute().onEach { dataState ->
            when(dataState){
                is DataState.Success ->{
                    _moviesState.value  = dataState.copy(dataState.data)
                }
                is DataState.Error ->{
                    _moviesState.value  = dataState.copy(dataState.exception)
                }
                is DataState.Loading ->{
                    _moviesState.value  = dataState.copy(dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

}