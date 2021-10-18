package com.bogdanov.strava.models

import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat
import java.time.Clock
import java.time.format.DateTimeFormatter
import java.util.*

@JsonClass(generateAdapter = true)
data class Workout(
    val name: String,
    val distance: Float,
    val moving_time: Int,
    val location_country : String,
    val average_speed: Float,
    val id: Long,
    val start_date: String
)

