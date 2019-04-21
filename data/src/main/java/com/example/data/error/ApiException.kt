package com.example.data.error

import com.squareup.moshi.JsonEncodingException
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class ApiException private constructor(
    message: String?,
    private val response: Response<*>?,
    val error: Throwable,
    val kind: Kind,
    private val retrofit: Retrofit?
) :
    RuntimeException(message, error) {

    sealed class Kind {
        object Network : Kind()
        object Http : Kind()
        object Unexpected : Kind()
    }

    fun <T> getErrorBodyAs(type: Class<T>): T? {
        if (response?.errorBody() == null) {
            return null
        }
        val converter = retrofit?.responseBodyConverter<T>(type, arrayOfNulls<Annotation>(0))
        return try {
            converter?.convert(response.errorBody()!!)
        } catch (e: IOException) {
            null
        } catch (e: JsonEncodingException) {
            null
        }
    }

    companion object {
        fun httpException(response: Response<*>, httpException: HttpException, retrofit: Retrofit) =
            ApiException(
                response.code().toString() + " " + response.message(),
                response,
                httpException,
                Kind.Http,
                retrofit
            )

        fun networkException(exception: IOException) =
            ApiException(exception.message, null, exception, Kind.Network, null)

        fun unexpectedException(exception: Throwable) =
            ApiException(exception.message, null, exception, Kind.Unexpected, null)
    }
}