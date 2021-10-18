package com.bogdanov.strava.network

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.dataStore
import com.bogdanov.strava.MainActivity
import com.bogdanov.strava.StravaApplication
import com.bogdanov.strava.auth.AuthConfig
import okhttp3.Interceptor
import okhttp3.Response

class AddTokenHeaderInterceptor(context: Context) : Interceptor {

    private val sharedPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE)
    private val token = sharedPreferences.getString("token", null)

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        println(token)
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