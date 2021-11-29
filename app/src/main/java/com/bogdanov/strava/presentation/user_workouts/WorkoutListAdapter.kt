package com.bogdanov.strava.presentation.user_workouts

import androidx.recyclerview.widget.DiffUtil
import com.bogdanov.strava.domain.model.Workout
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class WorkoutListAdapter: AsyncListDifferDelegationAdapter<Workout>(
    ActivityDiffCallback()
) {

    init {
        delegatesManager
            .addDelegate(WorkoutDelegate())
    }

    class ActivityDiffCallback : DiffUtil.ItemCallback<Workout>() {
        override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem == newItem
        }

    }
}