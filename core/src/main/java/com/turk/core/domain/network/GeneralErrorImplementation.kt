package com.turk.core.domain.network

import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import retrofit2.Response
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.UnknownHostException

fun Throwable.getError(): ErrorEntity {
    return when (this) {

        is JsonSyntaxException -> ErrorEntity.JsonSyntaxException
        is IllegalStateException -> ErrorEntity.IllegalStateException
        is ConnectException,
        is UnknownHostException,
            -> ErrorEntity.NetworkConnection

        is MalformedJsonException -> ErrorEntity.MalFormedJson
        is SecurityException -> ErrorEntity.AndroidError
        else -> ErrorEntity.ServerError
    }
}

fun <T> Response<T>.getHttpErrors(): ErrorEntity {
    return when (this.code()) {
        HttpURLConnection.HTTP_FORBIDDEN,
        HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorEntity.AuthError

        HttpURLConnection.HTTP_BAD_REQUEST -> ErrorEntity.BadRequest
        HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
        HttpURLConnection.HTTP_UNSUPPORTED_TYPE -> ErrorEntity.UnSupportedMediaType
        HttpURLConnection.HTTP_INTERNAL_ERROR -> {
            ErrorEntity.ServerError
        }

        else -> ErrorEntity.Unknown(Throwable(message = this.message()))
    }
}
