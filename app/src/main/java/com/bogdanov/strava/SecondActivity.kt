package com.bogdanov.strava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate ${hashCode()}")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart ${hashCode()}")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.d("onRestart ${hashCode()}")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume ${hashCode()}")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause ${hashCode()}")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("onStop ${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy ${hashCode()}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.d("onSavedInstanceState ${hashCode()}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.d("onRestoreInstanceState ${hashCode()}")
    }
}