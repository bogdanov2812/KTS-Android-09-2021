package com.bogdanov.strava.network

import com.bogdanov.strava.auth.AuthConfig
import okhttp3.Interceptor
import okhttp3.Response

class AddTokenHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = AuthConfig.token
        println(token)
        val originalRequest = chain.request()

        val request = if (token != null) {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(request)
    }
}