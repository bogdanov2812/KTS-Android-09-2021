package com.bogdanov.strava.network

import com.bogdanov.strava.models.User
import com.bogdanov.strava.models.Workout

class NetworkRepository {
    suspend fun userInfo(): User{
        return Networking.stravaApi.userInfo()
    }

    suspend fun userWorkouts(): List<Workout>{
        return Networking.stravaApi.userWorkouts()
    }
}