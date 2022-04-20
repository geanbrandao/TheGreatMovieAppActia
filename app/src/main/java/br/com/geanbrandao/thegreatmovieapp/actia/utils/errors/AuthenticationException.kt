package br.com.geanbrandao.thegreatmovieapp.actia.utils.errors

class AuthenticationException(val errorCode: Int, val errorMessage: String) : Exception()