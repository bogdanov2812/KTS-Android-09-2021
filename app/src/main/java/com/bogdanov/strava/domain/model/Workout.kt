package com.bogdanov.strava.domain.model

data class Workout(
    val id: Long,
    val name: String,
    val distance: Float,
    val movingTime: Int,
    val locationCountry : String,
    val averageSpeed: Float,
    val startDate: String,
    val userId: Long
) {
}