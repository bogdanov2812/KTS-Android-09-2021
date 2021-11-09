package com.bogdanov.strava.data.remote.dto


import com.bogdanov.strava.data.local.entity.UserEntity
import com.bogdanov.strava.domain.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "city")
    val city: String?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "firstname")
    val firstname: String,
    @Json(name = "follower_count")
    val followerCount: Int,
    @Json(name = "friend_count")
    val friendCount: Int,
    @Json(name = "id")
    val id: Long,
    @Json(name = "lastname")
    val lastname: String,
    @Json(name = "premium")
    val premium: Boolean,
    @Json(name = "profile")
    val profile: String?,
    @Json(name = "profile_medium")
    val profileMedium: String?,
    @Json(name = "sex")
    val sex: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "username")
    val username: String?,
    @Json(name = "weight")
    val weight: Float?
){
    fun toDomainModel() = User(id, firstname, lastname, city, country, weight, profile)

    fun toEntity() = UserEntity(id, firstname, lastname, city, country, weight, profile)
}