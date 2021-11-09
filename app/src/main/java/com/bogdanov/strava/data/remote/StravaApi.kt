package com.bogdanov.strava.data.remote

import com.bogdanov.strava.data.remote.dto.UserDto
import com.bogdanov.strava.data.remote.dto.WorkoutDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface StravaApi {
    @GET("athlete")
    suspend fun userInfo(): UserDto

    @GET("athlete/activities")
    suspend fun userWorkouts(): Response<List<WorkoutDto>>

    @PUT("athlete")
    suspend fun updateUserInfo(
        @Query("weight") weight: Float
    ): Response<UserDto>

    //
    @GET("athlete")
    suspend fun userInfoResponse(): Response<UserDto>

    @POST("activities")
    suspend fun addActivities(
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("start_date_local") date: String,
        @Query("elapsed_time") duration: Int,
        @Query("description") description: String,
        @Query("distance") distance: Float
    ): Response<WorkoutDto>
}