package com.bogdanov.strava.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val firstname: String = "firstname",
    val lastname: String = "lastname",
    val city: String = "city",
    val country: String = "countru",
    val weight: Float = 0f,
    @Json(name = "profile")
    val avatar: String? = null,
)
