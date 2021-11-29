package com.bogdanov.strava.data.local.entity

import androidx.room.*
import com.bogdanov.strava.data.local.entity.contracts.UserEntityContract
import com.bogdanov.strava.data.local.entity.contracts.WorkoutEntityContracts
import com.bogdanov.strava.domain.model.Workout

@Entity(
    tableName = WorkoutEntityContracts.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = [UserEntityContract.Columns.ID],
            childColumns = [WorkoutEntityContracts.Columns.USER_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class WorkoutEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = WorkoutEntityContracts.Columns.ID)
    val id: Long,
    @ColumnInfo(name = WorkoutEntityContracts.Columns.NAME)
    val name: String,
    @ColumnInfo(name = WorkoutEntityContracts.Columns.DISTANCE)
    val distance: Float,
    @ColumnInfo(name = WorkoutEntityContracts.Columns.MOVING_TIME)
    val movingTime: Int,
    @ColumnInfo(name = WorkoutEntityContracts.Columns.LOCATION_COUNTRY)
    val locationCountry : String,
    @ColumnInfo(name = WorkoutEntityContracts.Columns.AVERAGE_SPEED)
    val averageSpeed: Float,
    @ColumnInfo(name = WorkoutEntityContracts.Columns.START_DATA)
    val startDate: String,
    @ColumnInfo(name = WorkoutEntityContracts.Columns.USER_ID)
    val userId: Long
){
    fun toDomainModel() = Workout(id, name, distance, movingTime, locationCountry, averageSpeed, startDate, userId)
}


