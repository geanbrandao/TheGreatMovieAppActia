package br.com.geanbrandao.thegreatmovieapp.actia.utils

import br.com.geanbrandao.thegreatmovieapp.actia.utils.errors.AuthenticationException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException

fun Exception.resolveError(): State.ErrorState {

    val error = when (this) {
        is SocketTimeoutException -> {
            NetworkErrorException(errorMessage = "Connection error.")
        }
        is ConnectException -> {
            NetworkErrorException(errorMessage = "No internet access.")
        }
        is UnknownHostException -> {
            NetworkErrorException(errorMessage = "No internet access.")
        }
        is HttpException -> {
            when (this.code()) {
                502 -> NetworkErrorException(this.code(),  "Internal server error!")
                401 -> AuthenticationException(this.code(), "Authentication error!")
                400 -> NetworkErrorException.parseException(this)
                else -> this
            }
        }
        else -> this
    }
    return State.ErrorState(error)
}