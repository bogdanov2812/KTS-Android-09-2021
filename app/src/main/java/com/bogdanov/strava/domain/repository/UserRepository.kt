package com.bogdanov.strava.domain.repository

import com.bogdanov.strava.utils.Resource
import com.bogdanov.strava.data.local.entity.UserEntity
import com.bogdanov.strava.domain.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {

    suspend fun updateUserInfo(weight: Float)

    suspend fun getUserInfo1(): Resource<User>

    suspend fun getUserInfo(): Flow<Resource<User>>

    suspend fun saveUser(user: UserEntity)

    suspend fun deleteAllUsers()
}