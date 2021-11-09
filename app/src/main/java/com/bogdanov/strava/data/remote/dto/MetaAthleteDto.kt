package com.bogdanov.strava.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaAthleteDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "resource_state")
    val resourceState: Int
)