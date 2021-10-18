package com.bogdanov.strava.db

import android.provider.ContactsContract
import com.bogdanov.strava.models.Workout

class WorkoutRepository {

    private val workoutDao = Database.instance.workoutDao()

    suspend fun saveWorkouts(workouts: List<Workout>){
        workoutDao.insertWorkouts(workouts)
    }

    suspend fun getUserWorkouts(userId: Long): List<Workout>{
        return workoutDao.getUserWorkouts(userId)
    }
}