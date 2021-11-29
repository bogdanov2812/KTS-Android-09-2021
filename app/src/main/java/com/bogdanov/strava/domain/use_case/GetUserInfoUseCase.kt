package com.bogdanov.strava.domain.use_case

import com.bogdanov.strava.utils.Resource
import com.bogdanov.strava.domain.model.User
import com.bogdanov.strava.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserInfoUseCase(
    private val repository: UserRepository
) {
    suspend fun execute(): Resource<User> {
        return repository.getUserInfo1()
    }
}