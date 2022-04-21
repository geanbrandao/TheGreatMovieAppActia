package br.com.geanbrandao.thegreatmovieapp.actia.utils

import org.json.JSONObject
import retrofit2.HttpException

class NetworkErrorException(
    val errorCode: Int = -1,
    val errorMessage: String,
) : Exception() {
    override val message: String
        get() = localizedMessage

    override fun getLocalizedMessage(): String {
        return errorMessage
    }

    companion object {
        fun parseException(e: HttpException): NetworkErrorException {
            val errorBody = e.response()?.errorBody()?.string()
            return try {
                NetworkErrorException(e.code(), JSONObject(errorBody!!).getString("status_message"))
            } catch (_: Exception) {
                NetworkErrorException(e.code(), "Unexpected error!!ً")
            }
        }
    }
}
