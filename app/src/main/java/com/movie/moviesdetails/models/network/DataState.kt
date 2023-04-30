package com.movie.moviesdetails.models.network

sealed class DataState<T> {

    data class Success<T>(
        val data: T ?= null,
        val extraData: Any ?= null
    ): DataState<T>()

    data class Error<T>(
        val exception: Exception ?= null
    ): DataState<T>()

    data class Loading<T>(
        val progressBarState: ProgressBarState = ProgressBarState.Idle
    ): DataState<T>()

}

sealed class ProgressBarState{
    object Loading: ProgressBarState()
    object Idle: ProgressBarState()
}

enum class ResponseCodeHandler {
    UNAUTHORIZED,
    SERVER_ERROR,
    FORBIDDEN,
    SUCCESSFUL
}

object ErrorCode{

    fun getCodeStatus(code: Int): ResponseCodeHandler{
        return when(code) {
            in 200..300 -> {
                ResponseCodeHandler.SUCCESSFUL
            }
            401 -> {
                ResponseCodeHandler.UNAUTHORIZED
            }
            403 -> {
                ResponseCodeHandler.FORBIDDEN
            }
            else ->{
                ResponseCodeHandler.SERVER_ERROR
            }
        }
    }

}