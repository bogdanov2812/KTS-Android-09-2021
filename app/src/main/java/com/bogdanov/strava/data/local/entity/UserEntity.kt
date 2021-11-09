package com.bogdanov.strava.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bogdanov.strava.data.local.entity.contracts.UserEntityContract
import com.bogdanov.strava.domain.model.User

@Entity(
    tableName = UserEntityContract.TABLE_NAME,
)

data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = UserEntityContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = UserEntityContract.Columns.FIRST_NAME)
    val firstname: String,
    @ColumnInfo(name = UserEntityContract.Columns.LAST_NAME)
    val lastname: String,
    @ColumnInfo(name = UserEntityContract.Columns.CITY)
    val city: String?,
    @ColumnInfo(name = UserEntityContract.Columns.COUNTRY)
    val country: String?,
    @ColumnInfo(name = UserEntityContract.Columns.WEIGHT)
    val weight: Float?,
    @ColumnInfo(name = UserEntityContract.Columns.AVATAR)
    val avatar: String?,
){
    fun toDomainModel() = User(id, firstname, lastname, city, country, weight, avatar)
}
