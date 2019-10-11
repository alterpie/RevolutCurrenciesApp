package com.example.data

import android.net.ConnectivityManager
import com.example.data.error.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkErrorInterceptor @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!hasNetworkConnection()){
            throw NoConnectivityException()
        }

        val request = chain.request()

        return chain.proceed(request)
    }

    private fun hasNetworkConnection(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
