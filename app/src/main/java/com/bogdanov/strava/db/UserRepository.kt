package com.bogdanov.strava.db

import com.bogdanov.strava.models.User

class UserRepository {

    private val userDao = Database.instance.userDao()


    suspend fun getUserById(userId: Long): User? {
        return userDao.getUserById(userId)
    }

    suspend fun saveUser(user: User) {
        userDao.insertUsers(listOf(user))
    }
}