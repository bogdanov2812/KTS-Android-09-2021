package com.bogdanov.strava.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bogdanov.strava.models.*

@Database(
    entities = [
        User::class,
        Workout::class], version = ApplicationDatabase.DB_VERSION
)
@TypeConverters(MetaAthleteConverter::class)

abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app-database"
    }
}
