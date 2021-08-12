package com.example.baseproject.errors

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException

sealed class ErrorType {
    object NoInternet : ErrorType()
    object Unknown : ErrorType()
    data class Api(val message: String?) : ErrorType()
}

fun Throwable.asError(): ErrorType =
    when (val result = this) {
        is UnknownHostException -> ErrorType.NoInternet
        is ConnectException -> ErrorType.NoInternet
        is HttpException -> ErrorType.Api(result.message)
        is IOException -> ErrorType.Api(result.message)
        else -> ErrorType.Unknown
    }