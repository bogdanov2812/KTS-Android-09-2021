package com.bogdanov.strava.onboarding

import android.content.Context
import androidx.fragment.app.Fragment

open class OnBoardingFragment(layout: Int) : Fragment(layout) {

    fun onBoardingFinish(){
        val sharedPreferences = requireContext().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        sharedPreferences.edit()
            .putBoolean("Finished", true)
            .apply()
    }
}