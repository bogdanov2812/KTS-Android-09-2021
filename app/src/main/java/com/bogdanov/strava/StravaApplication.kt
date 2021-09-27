package com.bogdanov.strava

import android.app.Application
import timber.log.Timber

class StravaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }
}