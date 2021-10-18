package com.bogdanov.strava.network

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber

object Networking {

    val stravaApi: StravaApi
        get() = retrofit.create()

    lateinit var retrofit: Retrofit

    fun init(context: Context){
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AddTokenHeaderInterceptor(context))
            .addInterceptor(HttpLoggingInterceptor {
                Timber.tag("Network").d(it)
            }
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://www.strava.com/api/v3/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}