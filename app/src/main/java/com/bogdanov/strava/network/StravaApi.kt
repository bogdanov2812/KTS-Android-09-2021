package com.bogdanov.strava.network

import com.bogdanov.strava.models.User
import com.bogdanov.strava.models.Workout
import retrofit2.http.GET
import retrofit2.http.Path

interface StravaApi {
    @GET("athlete")
    suspend fun userInfo(): User

    @GET("athlete/activities")
    suspend fun userWorkouts(): List<Workout>


}