package com.bogdanov.strava.domain.use_case

import com.bogdanov.strava.domain.model.User
import com.bogdanov.strava.domain.repository.UserRepository

class UpdateUserInfoUserCase(
    private val repository: UserRepository
) {
    suspend fun execute(weight: Float){
        repository.updateUserInfo(weight)
    }

}