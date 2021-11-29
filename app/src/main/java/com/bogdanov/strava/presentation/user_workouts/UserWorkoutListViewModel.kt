package com.bogdanov.strava.presentation.user_workouts

import androidx.lifecycle.*
import com.bogdanov.strava.R
import com.bogdanov.strava.datastore.SharedPrefs
import com.bogdanov.strava.data.repository.WorkoutRepositoryImpl
import com.bogdanov.strava.domain.model.Workout
import com.bogdanov.strava.domain.repository.WorkoutRepository
import com.bogdanov.strava.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserWorkoutListViewModel(
    private val workoutRepository: WorkoutRepository,
    private val sharedPrefs: SharedPrefs
): ViewModel() {

    private val _userWorkouts = MutableStateFlow<List<Workout>?>(emptyList())

    private var currentInfoJob: Job? = null

    val userWorkouts: StateFlow<List<Workout>?> = _userWorkouts

    private val _actionToast = MutableLiveData<Int>()
    val actionToast: LiveData<Int> = _actionToast

    fun getUserWorkouts(){
        currentInfoJob?.cancel()
        viewModelScope.launch {
            workoutRepository.getUserWorkouts(sharedPrefs.currentUserId ?: 0L).collect {
                when(it){
                    is Resource.SuccessFromApi ->{
                        _userWorkouts.emit(it.data)
                        _actionToast.postValue(R.string.data_from_api)
                    }
                    is Resource.SuccessFromDb ->{
                        _userWorkouts.emit(it.data)
                        _actionToast.postValue(R.string.data_from_db)
                    }
                    is Resource.Loading ->{
                    }
                    is Resource.Error ->{
                    }
                }
            }

        }
    }

}