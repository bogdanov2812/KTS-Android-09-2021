package com.bogdanov.strava.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bogdanov.strava.data.local.entity.UserEntity
import com.bogdanov.strava.data.local.entity.contracts.UserEntityContract

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(userEntities: List<UserEntity>)

    @Query("SELECT * FROM ${UserEntityContract.TABLE_NAME} WHERE ${UserEntityContract.Columns.ID} = :userId")
    suspend fun getUserById(userId: Long): UserEntity?

    @Query("DELETE FROM ${UserEntityContract.TABLE_NAME}")
    suspend fun removeAll()
}