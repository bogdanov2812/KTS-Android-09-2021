package com.bogdanov.strava.models

object UserContracts {
    const val TABLE_NAME = "users"

    object Columns {
        const val ID = "id"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val AVATAR = "avatar"
        const val WEIGHT = "weight"
        const val CITY = "city"
        const val COUNTRY = "country"
    }
}