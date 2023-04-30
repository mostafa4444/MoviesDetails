package com.movie.moviesdetails.usecases.movies

import com.movie.moviesdetails.models.movies.MovieFilterResponse
import com.movie.moviesdetails.models.movies.MoviesResponse
import com.movie.moviesdetails.models.network.DataState
import com.movie.moviesdetails.models.network.ErrorCode
import com.movie.moviesdetails.models.network.ProgressBarState
import com.movie.moviesdetails.models.network.ResponseCodeHandler
import com.movie.moviesdetails.network.movies.MovieRepository
import com.movie.moviesdetails.network.movies.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    fun execute(movieId: Int): Flow<DataState<MovieFilterResponse>> =
        flow<DataState<MovieFilterResponse>>{
            try {
                emit(DataState.Loading(ProgressBarState.Loading))
                val data = movieRepository.getMovieById(movieId)
                ErrorCode.getCodeStatus(data.code()).let {
                    emit(DataState.Loading(ProgressBarState.Idle))
                    when(it){
                        ResponseCodeHandler.SUCCESSFUL->{
                            emit(DataState.Success(data.body()))
                        }
                        else ->{
                            emit(DataState.Error(exception = Exception()))
                        }
                    }
                }
            }
            catch (e: Exception){
                emit(DataState.Loading(ProgressBarState.Idle))
                emit(DataState.Error(exception = e))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
}