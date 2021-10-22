package com.bogdanov.strava.user_workouts

import android.app.Application
import androidx.lifecycle.*
import com.bogdanov.strava.R
import com.bogdanov.strava.datastore.SharedPrefs
import com.bogdanov.strava.db.WorkoutRepository
import com.bogdanov.strava.models.Workout
import com.bogdanov.strava.network.NetworkRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class UserWorkoutListViewModel(
    private val repository: NetworkRepository,
    private val workoutRepository: WorkoutRepository
): ViewModel() {

    private val userWorkoutsLiveData = MutableLiveData<List<Workout>>(emptyList())

    private val _failToast = MutableLiveData<Int>()

    private var currentInfoJob: Job? = null

    val userWorkouts: LiveData<List<Workout>>
        get() = userWorkoutsLiveData

    val failToast: LiveData<Int>
        get() = _failToast

    fun getUserWorkouts(){
        currentInfoJob?.cancel()
        currentInfoJob = viewModelScope.launch {
            runCatching {
                repository.userWorkouts()
            }.onSuccess {
                userWorkoutsLiveData.postValue(it)
                if (it != SharedPrefs.currentUserId?.let { it1 ->
                        workoutRepository.getUserWorkouts(
                            it1
                        )
                    }){
                    workoutRepository.saveWorkouts(it)
                }
            }.onFailure {
                try {
                    val workouts = SharedPrefs.currentUserId?.let { it1 ->
                        workoutRepository.getUserWorkouts(
                            it1
                        )
                    }
                    userWorkoutsLiveData.postValue(workouts!!)
                    _failToast.postValue(R.string.data_from_db)
                }catch (t: Throwable){
                    _failToast.postValue(R.string.fail_load_data)
                    userWorkoutsLiveData.postValue(emptyList())
                }
            }
        }
    }

}