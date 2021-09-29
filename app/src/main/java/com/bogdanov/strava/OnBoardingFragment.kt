package com.bogdanov.strava

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.enter).setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingPage_to_loginPage)
        }
    }
}