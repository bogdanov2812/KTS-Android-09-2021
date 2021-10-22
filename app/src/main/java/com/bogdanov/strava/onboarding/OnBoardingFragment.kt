package com.bogdanov.strava.onboarding

import android.content.Context
import androidx.fragment.app.Fragment
import com.bogdanov.strava.datastore.SharedPrefs

open class OnBoardingFragment(layout: Int) : Fragment(layout) {

    fun onBoardingFinish(){
        SharedPrefs.onBoardingCheck = true
    }
}