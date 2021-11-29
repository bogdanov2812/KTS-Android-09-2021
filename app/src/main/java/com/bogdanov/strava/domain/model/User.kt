package com.bogdanov.strava.domain.model

import com.bogdanov.strava.data.local.entity.UserEntity

data class User(
    val id: Long,
    val firstname: String,
    val lastname: String,
    val city: String?,
    val country: String?,
    val weight: Float?,
    val avatar: String?,
){
    fun toEntity() = UserEntity(id, firstname, lastname, city, country, weight, avatar)
}
