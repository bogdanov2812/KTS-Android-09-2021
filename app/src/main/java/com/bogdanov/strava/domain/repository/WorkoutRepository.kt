package com.bogdanov.strava.domain.repository

import com.bogdanov.strava.domain.model.Workout
import com.bogdanov.strava.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    suspend fun getUserWorkouts(userId: Long): Flow<Resource<List<Workout>>>

    suspend fun addWorkout(name: String, type: String, date: String, duration: Int, description: String, distance: Float)

}