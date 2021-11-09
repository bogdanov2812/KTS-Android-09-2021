package com.bogdanov.strava.network

import com.bogdanov.strava.data.remote.StravaApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber

object Networking {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AddTokenHeaderInterceptor())
        .addInterceptor(HttpLoggingInterceptor {
            Timber.tag("Network").d(it)
        }
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.strava.com/api/v3/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val stravaApi: StravaApi
        get() = retrofit.create()

}