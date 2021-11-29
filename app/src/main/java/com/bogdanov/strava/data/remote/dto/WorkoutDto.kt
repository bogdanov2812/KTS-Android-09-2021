package com.bogdanov.strava.data.remote.dto


import com.bogdanov.strava.data.local.entity.WorkoutEntity
import com.bogdanov.strava.domain.model.Workout
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WorkoutDto(
    @Json(name = "achievement_count")
    val achievementCount: Int,
    @Json(name = "athlete")
    val metaAthlete: MetaAthleteDto,
    @Json(name = "athlete_count")
    val athleteCount: Int,
    @Json(name = "comment_count")
    val commentCount: Int,
    @Json(name = "distance")
    val distance: Float,
    @Json(name = "elapsed_time")
    val elapsedTime: Int,
    @Json(name = "end_latlng")
    val endLatlng: Any?,
    @Json(name = "id")
    val id: Long,
    @Json(name = "kudos_count")
    val kudosCount: Int,
    @Json(name = "location_city")
    val locationCity: Any?,
    @Json(name = "location_country")
    val locationCountry: String,
    @Json(name = "moving_time")
    val movingTime: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "photo_count")
    val photoCount: Int,
    @Json(name = "start_date")
    val startDate: String,
    @Json(name = "start_date_local")
    val startDateLocal: String,
    @Json(name = "timezone")
    val timezone: String,
    @Json(name = "total_elevation_gain")
    val totalElevationGain: Int,
    @Json(name = "type")
    val type: String,
    @Json(name = "average_speed")
    val averageSpeed: Float,
){
    fun toDomainModel() = Workout(id, name, distance, movingTime, locationCountry, averageSpeed, startDate, id)

    fun toEntity() = WorkoutEntity(id,name, distance, movingTime, locationCountry, averageSpeed, startDate, metaAthlete.id)
}