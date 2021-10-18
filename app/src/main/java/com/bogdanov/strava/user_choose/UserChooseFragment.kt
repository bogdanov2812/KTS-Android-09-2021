package com.bogdanov.strava.user_choose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.auth.AuthConfig
import com.bogdanov.strava.databinding.FragmentUserChooseBinding

class UserChooseFragment: Fragment(R.layout.fragment_user_choose) {

    private val binding: FragmentUserChooseBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUserActivities.setOnClickListener {
            findNavController().navigate(R.id.action_userChooseFragment_to_userWorkoutListFragment)
        }

        binding.buttonUserInfo.setOnClickListener {
            findNavController().navigate((R.id.action_userChooseFragment_to_userInfoFragment))
        }
    }
}