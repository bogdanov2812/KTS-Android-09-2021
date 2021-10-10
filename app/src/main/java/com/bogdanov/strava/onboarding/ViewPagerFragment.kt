package com.bogdanov.strava.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.FragmentViewPagerBinding
import com.bogdanov.strava.onboarding.screens.OnBoardingFirstFragment
import com.bogdanov.strava.onboarding.screens.OnBoardingSecondFragment
import com.bogdanov.strava.onboarding.screens.OnBoardingThirdFragment

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {
    private val binding : FragmentViewPagerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            OnBoardingFirstFragment(),
            OnBoardingSecondFragment(),
            OnBoardingThirdFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
        binding.indicator.attachToPager(binding.viewPager)
    }
}