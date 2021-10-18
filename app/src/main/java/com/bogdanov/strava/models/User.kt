package com.bogdanov.strava.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val firstname: String,
    val lastname: String,
    val city: String?,
    val country: String?,
    val weight: Float?,
    @Json(name = "profile")
    val avatar: String? = null,
)
