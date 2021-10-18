package com.bogdanov.strava.onboarding.screens

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.FragmentOnBoardingThirdBinding
import com.bogdanov.strava.onboarding.OnBoardingFragment

class OnBoardingThirdFragment : OnBoardingFragment(R.layout.fragment_on_boarding_third){
    private val binding: FragmentOnBoardingThirdBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFinish.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_authFragment)
            onBoardingFinish()
        }
    }
}