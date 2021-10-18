package com.bogdanov.strava.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bogdanov.strava.models.User
import com.bogdanov.strava.models.UserContracts

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<User>)

    @Query("SELECT * FROM ${UserContracts.TABLE_NAME} WHERE ${UserContracts.Columns.ID} = :userId")
    suspend fun getUserById(userId: Long): User?
}