package com.example.domain.error

sealed class AppError {
    data class Api(val error: Error?, val statusCode: Int) : AppError()
    object Network : AppError()
    data class Unexpected(val throwable: Throwable) : AppError()
}