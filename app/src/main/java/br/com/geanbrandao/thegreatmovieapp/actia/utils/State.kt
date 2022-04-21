package br.com.geanbrandao.thegreatmovieapp.actia.utils

sealed class State<out T> {
    data class LoadingState(val isLoading: Boolean) : State<Nothing>()
    data class ErrorState(val exception: Throwable) : State<Nothing>()
    data class DataState<T>(val data: T) : State<T>()
}
