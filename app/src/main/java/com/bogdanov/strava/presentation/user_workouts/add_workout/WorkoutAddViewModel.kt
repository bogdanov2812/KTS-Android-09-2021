package com.bogdanov.strava.presentation.user_workouts.add_workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bogdanov.strava.R
import com.bogdanov.strava.domain.repository.WorkoutRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class WorkoutAddViewModel(
    private val workoutRepository: WorkoutRepository
): ViewModel() {

    private val saveError = Channel<Int>(Channel.BUFFERED)
    private val saveSuccess = Channel<Unit>(Channel.BUFFERED)

    val saveSuccessFlow: Flow<Unit>
        get() = saveSuccess.receiveAsFlow()

    val saveErrorFlow: Flow<Int>
        get() = saveError.receiveAsFlow()

    fun saveWorkout(name: String, type: String, date: String, duration: String, description: String, distance: String){
        viewModelScope.launch {
            try {
                workoutRepository.addWorkout(name, type, date, duration.toInt(), description, distance.toFloat())
                saveSuccess.send(Unit)

            }catch (t: Throwable){
                Timber.e(t)
                saveError.send(R.string.fail_load_data)
            }

        }

    }
}