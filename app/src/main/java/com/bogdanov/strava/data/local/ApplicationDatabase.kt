package com.bogdanov.strava.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bogdanov.strava.data.local.dao.UserDao
import com.bogdanov.strava.data.local.dao.WorkoutDao
import com.bogdanov.strava.data.remote.converters.MetaAthleteConverter
import com.bogdanov.strava.data.local.entity.UserEntity
import com.bogdanov.strava.data.local.entity.WorkoutEntity

@Database(
    entities = [
        UserEntity::class,
        WorkoutEntity::class], version = ApplicationDatabase.DB_VERSION
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
