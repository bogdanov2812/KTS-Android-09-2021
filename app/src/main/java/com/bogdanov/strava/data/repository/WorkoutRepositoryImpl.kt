package com.bogdanov.strava.data.repository

import com.bogdanov.strava.data.local.dao.WorkoutDao
import com.bogdanov.strava.data.remote.StravaApi
import com.bogdanov.strava.data.remote.dto.WorkoutDto
import com.bogdanov.strava.datastore.SharedPrefs
import com.bogdanov.strava.domain.model.Workout
import com.bogdanov.strava.domain.repository.WorkoutRepository
import com.bogdanov.strava.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber

class WorkoutRepositoryImpl(
    private val dao: WorkoutDao,
    private val api: StravaApi,
    private val sharedPrefs: SharedPrefs
): WorkoutRepository {

    override suspend fun getUserWorkouts(userId: Long): Flow<Resource<List<Workout>>> = flow {
        Timber.d("FLOW STARTED")
        try{
            emit(Resource.Loading())

            val response = api.userWorkouts()

            val workoutDtoList = response.body()

            Timber.d("$workoutDtoList")

            if (response.isSuccessful && workoutDtoList != null){

                Timber.d("SUCCESS")

                val workoutEntityList = workoutDtoList.map {
                    it.toEntity()
                }
                dao.insertWorkouts(workoutEntityList)

                val workoutDomainList = workoutDtoList.map {
                    it.toDomainModel()
                }

                emit(Resource.SuccessFromApi(workoutDomainList))
            }

        }catch (t: Throwable){
            emit(Resource.Error(message = "Error from API"))

            Timber.e(t)

            try {
                emit(Resource.Loading())
                val workoutEntityList = dao.getUserWorkouts(sharedPrefs.currentUserId ?: 0L)
                if (workoutEntityList != null) {
                    val workoutDomainList = workoutEntityList.map {
                        it.toDomainModel()
                    }
                    emit(Resource.SuccessFromDb(workoutDomainList))
                }
            }catch (t: Throwable){
                emit(Resource.Error(message = "Error in DB"))
            }
        }
    }

    override suspend fun addWorkout(
        name: String,
        type: String,
        date: String,
        duration: Int,
        description: String,
        distance: Float
    ) {
        try {
            val response = api.addActivities(name, type, date, duration, description, distance)

            val result = response.body()

            if (response.isSuccessful && result != null){
                dao.insertWorkouts(listOf(result.toEntity()))
            }
        }catch (t: Throwable){

        }
    }
}