package com.bogdanov.strava.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bogdanov.strava.data.local.entity.WorkoutEntity
import com.bogdanov.strava.data.local.entity.contracts.WorkoutEntityContracts

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkouts(workouts: List<WorkoutEntity>)

    @Query("SELECT * FROM ${WorkoutEntityContracts.TABLE_NAME} WHERE ${WorkoutEntityContracts.Columns.USER_ID} = :userId")
    suspend fun getUserWorkouts(userId: Long): List<WorkoutEntity>?
}