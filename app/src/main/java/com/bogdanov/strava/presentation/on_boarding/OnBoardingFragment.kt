package com.bogdanov.strava.presentation.on_boarding

import android.content.Context
import androidx.fragment.app.Fragment
import com.bogdanov.strava.datastore.SharedPrefs

open class OnBoardingFragment(layout: Int) : Fragment(layout) {

    fun onBoardingFinish(){
        SharedPrefs.onBoardingCheck = true
    }
}