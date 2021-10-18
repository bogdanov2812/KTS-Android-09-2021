package com.bogdanov.strava.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(
    tableName = UserContracts.TABLE_NAME,
)

@JsonClass(generateAdapter = true)
data class User(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = UserContracts.Columns.ID)
    val id: Long,
    @ColumnInfo(name = UserContracts.Columns.FIRST_NAME)
    val firstname: String,
    @ColumnInfo(name = UserContracts.Columns.LAST_NAME)
    val lastname: String,
    @ColumnInfo(name = UserContracts.Columns.CITY)
    val city: String,
    @ColumnInfo(name = UserContracts.Columns.COUNTRY)
    val country: String,
    @ColumnInfo(name = UserContracts.Columns.WEIGHT)
    val weight: Float,
    @Json(name = "profile")
    @ColumnInfo(name = UserContracts.Columns.AVATAR)
    val avatar: String?,
)
