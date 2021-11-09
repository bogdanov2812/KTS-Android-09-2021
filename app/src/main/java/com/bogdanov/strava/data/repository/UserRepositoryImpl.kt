package com.bogdanov.strava.data.repository

import com.bogdanov.strava.utils.Resource
import com.bogdanov.strava.data.local.dao.UserDao
import com.bogdanov.strava.data.local.entity.UserEntity
import com.bogdanov.strava.data.remote.StravaApi
import com.bogdanov.strava.datastore.SharedPrefs
import com.bogdanov.strava.domain.model.User
import com.bogdanov.strava.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class UserRepositoryImpl(
    private val api: StravaApi,
    private val dao: UserDao
): UserRepository {


    override suspend fun getUserInfo1(): Resource<User> {

        try {
            val response = api.userInfoResponse()

            val result = response.body()

            if (response.isSuccessful && result != null) return Resource.SuccessFromApi(data = result.toDomainModel())

        } catch (t: Throwable) {
            return try {
                val userEntity = dao.getUserById(SharedPrefs.currentUserId ?: 0L)

                if (userEntity != null) {
                    Resource.SuccessFromDb(data = userEntity.toDomainModel())
                } else {
                    Resource.Error(message = "Такого пользователя нет в БД")
                }
            } catch (t: Throwable) {
                Resource.Error(message = "Ошибка при запросе в БД")
            }
        }
        return Resource.Error(message = "Необъяснимая ошибка")
    }




    override suspend fun updateUserInfo(weight: Float) {
        try {
            val response = api.updateUserInfo(weight)

            val result = response.body()

            if (response.isSuccessful && result != null)
            {
                dao.insertUsers(listOf(result.toEntity()))
            }


        }catch (t: Throwable){
            Timber.e(t)
        }
    }


    override suspend fun getUserInfo(): Flow<Resource<User>> = flow {
        Timber.d("FLOW STARTED")
        try {
            emit(Resource.Loading())
            val response = api.userInfoResponse()

            val userDto = response.body()

            if (response.isSuccessful && userDto != null){
                dao.insertUsers(listOf(userDto.toEntity()))
                emit(Resource.SuccessFromApi(userDto.toDomainModel()))
            }
        }catch (t: Throwable){
            emit(Resource.Error(message = "Error from API"))
            try {
                emit(Resource.Loading())
                val userEntity = dao.getUserById(SharedPrefs.currentUserId ?: 0L)
                if (userEntity != null) {
                    emit(Resource.SuccessFromDb(userEntity.toDomainModel()))
                }
            }catch (t: Throwable){
                emit(Resource.Error(message = "Error in DB"))
            }

        }
    }

    override suspend fun saveUser(user: UserEntity) {
        try {
            dao.insertUsers(listOf(user))
        }catch(t: Throwable){
            Timber.e(t)
        }
    }

    override suspend fun deleteAllUsers() {
        try {
            dao.removeAll()
        }catch (t: Throwable){
            Timber.e(t)
        }
    }
}