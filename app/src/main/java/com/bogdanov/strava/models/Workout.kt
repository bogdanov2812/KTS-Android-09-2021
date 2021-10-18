package com.bogdanov.strava.models

import androidx.room.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat
import java.time.Clock
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(
    tableName = WorkoutContracts.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = [UserContracts.Columns.ID],
            childColumns = [WorkoutContracts.Columns.USER_ID]
        )
    ]
)

@JsonClass(generateAdapter = true)
data class Workout(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = WorkoutContracts.Columns.ID)
    val id: Long,
    @ColumnInfo(name = WorkoutContracts.Columns.NAME)
    val name: String,
    @ColumnInfo(name = WorkoutContracts.Columns.DISTANCE)
    val distance: Float,
    @ColumnInfo(name = WorkoutContracts.Columns.MOVING_TIME)
    val moving_time: Int,
    @ColumnInfo(name = WorkoutContracts.Columns.LOCATION_COUNTRY)
    val location_country : String,
    @ColumnInfo(name = WorkoutContracts.Columns.AVERAGE_SPEED)
    val average_speed: Float,
    @ColumnInfo(name = WorkoutContracts.Columns.START_DATA)
    val start_date: String,
    @ColumnInfo(name = WorkoutContracts.Columns.USER_ID)
    @Json(name = "athlete")
    val user_id: MetaAthlete
)


