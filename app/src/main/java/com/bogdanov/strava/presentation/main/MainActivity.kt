package com.bogdanov.strava.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bogdanov.strava.R
import com.bogdanov.strava.datastore.SharedPrefs
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment)

        val graph = navController.graph
        graph.startDestination = when {
            !SharedPrefs.onBoardingCheck -> R.id.viewPagerFragment
            SharedPrefs.authToken.isNullOrBlank() -> R.id.authFragment
            else -> R.id.userWorkoutListFragment
        }

        navController.graph = graph

        bottomNavigationView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id){
                R.id.viewPagerFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.authFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }

                else -> bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}