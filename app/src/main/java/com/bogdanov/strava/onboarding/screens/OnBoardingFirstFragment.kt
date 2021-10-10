package com.bogdanov.strava.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.FragmentOnBoardingFirstBinding

class OnBoardingFirstFragment : Fragment(R.layout.fragment_on_boarding_first){

    private val binding: FragmentOnBoardingFirstBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.buttonSkip.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginPage)
        }

        binding.buttonNext.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }
}