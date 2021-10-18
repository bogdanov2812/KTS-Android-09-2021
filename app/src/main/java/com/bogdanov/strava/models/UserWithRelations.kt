package com.bogdanov.strava.models

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithRelations(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = UserContracts.Columns.ID,
        entityColumn = WorkoutContracts.Columns.USER_ID
    )
    val workouts: List<Workout>
)
