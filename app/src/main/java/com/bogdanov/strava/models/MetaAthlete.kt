package com.bogdanov.strava.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaAthlete(
    val id: Long
)
