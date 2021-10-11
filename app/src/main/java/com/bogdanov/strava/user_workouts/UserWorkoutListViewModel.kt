package com.bogdanov.strava.user_workouts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bogdanov.strava.R
import com.bogdanov.strava.models.Workout
import com.bogdanov.strava.network.NetworkRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class UserWorkoutListViewModel: ViewModel() {

    private val repository = NetworkRepository()

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
            }.onFailure {
                Timber.e(it)
                _failToast.postValue(R.string.fail_load_data)
                userWorkoutsLiveData.postValue(emptyList())
            }
        }
    }
}