package com.bogdanov.strava.presentation.user_workouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.FragmentUserWorkoutListBinding
import com.bogdanov.strava.utils.autoCleared
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserWorkoutListFragment: Fragment(R.layout.fragment_user_workout_list) {
    private val viewModel: UserWorkoutListViewModel by viewModel()

    private val binding: FragmentUserWorkoutListBinding by viewBinding()

    private var workoutAdapter: WorkoutListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addWorkoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_userWorkoutListFragment_to_workoutAddFragment)
        }

        initList()

        initWorkoutObserver()

        initToastObserver()

        viewModel.getUserWorkouts()

    }

    private fun initList(){
        workoutAdapter = WorkoutListAdapter()
        binding.WorkoutList.apply{
            adapter = workoutAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun initWorkoutObserver(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.userWorkouts.collect {
                workoutAdapter.items = it
            }
        }
    }

    private fun initToastObserver(){
        viewModel.actionToast.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }
}