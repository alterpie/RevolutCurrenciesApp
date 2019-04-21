package com.example.data.error

import com.example.domain.error.AppError
import com.example.domain.error.Error
import com.example.domain.error.ErrorMapper
import retrofit2.HttpException

class ApiErrorMapper : ErrorMapper<AppError>() {
    override fun transform(throwable: Throwable): AppError =
        if (throwable is ApiException) {
            when (throwable.kind) {
                ApiException.Kind.Http -> AppError.Api(
                    throwable.getErrorBodyAs(Error::class.java),
                    (throwable.error as HttpException).response().code()
                )
                ApiException.Kind.Network -> AppError.Network
                ApiException.Kind.Unexpected -> AppError.Unexpected(throwable)
            }
        } else AppError.Unexpected(throwable)
}