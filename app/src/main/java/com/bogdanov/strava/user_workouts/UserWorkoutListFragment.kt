package com.bogdanov.strava.user_workouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.FragmentUserWorkoutListBinding
import com.bogdanov.strava.utils.autoCleared


class UserWorkoutListFragment: Fragment(R.layout.fragment_user_workout_list) {
    private val viewModel: UserWorkoutListViewModel by viewModels()

    private val binding: FragmentUserWorkoutListBinding by viewBinding()

    private var activityAdapter: WorkoutListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()

        viewModel.getUserWorkouts()

        viewModel.userWorkouts.observe(viewLifecycleOwner, {
            println(it)
            activityAdapter.items = it
        })

        viewModel.failToast.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initList(){
        activityAdapter = WorkoutListAdapter()
        binding.WorkoutList.apply{
            adapter = activityAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel(){
        viewModel.userWorkouts.observe(viewLifecycleOwner, {
            println(it)
            activityAdapter.items = it
        })
    }
}