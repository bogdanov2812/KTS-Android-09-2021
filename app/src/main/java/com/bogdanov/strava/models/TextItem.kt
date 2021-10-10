package com.bogdanov.strava.models

import java.util.*

data class TextItem(
    val title: String,
    var likes: Int,
    val author: String,
    val uuid: UUID
)
