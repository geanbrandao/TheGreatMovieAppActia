package br.com.geanbrandao.thegreatmovieapp.actia.utils.errors

import org.json.JSONObject
import retrofit2.HttpException

class AuthenticationException(private val errorMessage: String) : Exception() {
    override val message: String
        get() = localizedMessage

    override fun getLocalizedMessage(): String {
        return errorMessage
    }

    companion object {
        fun parseException(e: HttpException): AuthenticationException {
            val errorBody = e.response()?.errorBody()?.string()
            return try {
                AuthenticationException(JSONObject(errorBody!!).getString("status_message"))
            } catch (_: Exception) {
                AuthenticationException("Authentication error!ً")
            }
        }
    }
}