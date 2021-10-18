package com.bogdanov.strava.models

object WorkoutContracts {
    const val TABLE_NAME = "workouts"

    object Columns{
        const val ID = "id"
        const val USER_ID = "user_id"
        const val NAME = "name"
        const val DISTANCE = "distance"
        const val MOVING_TIME = "moving_time"
        const val LOCATION_COUNTRY = "location_country"
        const val AVERAGE_SPEED = "average_speed"
        const val START_DATA = "start_date"
    }

}