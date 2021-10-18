package com.bogdanov.strava.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bogdanov.strava.models.User
import com.bogdanov.strava.models.UserContracts
import com.bogdanov.strava.models.Workout
import com.bogdanov.strava.models.WorkoutContracts

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWorkouts(workouts: List<Workout>)

    @Query("SELECT * FROM ${WorkoutContracts.TABLE_NAME} WHERE ${WorkoutContracts.Columns.USER_ID} = :userId")
    suspend fun getUserWorkouts(userId: Long): List<Workout>
}