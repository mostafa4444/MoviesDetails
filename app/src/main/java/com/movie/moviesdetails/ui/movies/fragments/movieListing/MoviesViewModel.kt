package com.movie.moviesdetails.ui.movies.fragments.movieListing

import androidx.lifecycle.viewModelScope
import com.movie.moviesdetails.base.BaseViewModel
import com.movie.moviesdetails.models.movies.MoviesResponse
import com.movie.moviesdetails.models.network.DataState
import com.movie.moviesdetails.usecases.movies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel(){


    private val _getMoviesState: MutableSharedFlow<DataState<MoviesResponse>> = MutableSharedFlow()
    val moviesState = _getMoviesState.asSharedFlow()

    init {
        getMovies()
    }
    fun getMovies(){
        getMoviesUseCase.execute().onEach { dataState ->
            when(dataState){
                is DataState.Success ->{
                    _getMoviesState.emit(DataState.Success(dataState.data))
                }
                is DataState.Error ->{
                    _getMoviesState.emit(DataState.Error(dataState.exception))
                }
                is DataState.Loading ->{
                    _getMoviesState.emit(DataState.Loading(dataState.progressBarState))
                }
            }
        }.launchIn(viewModelScope)
    }

}