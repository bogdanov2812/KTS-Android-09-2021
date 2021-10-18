package com.bogdanov.strava

import android.app.Application
import com.bogdanov.strava.db.Database
import com.bogdanov.strava.network.Networking
import timber.log.Timber

class StravaApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Networking.init(this)
        Database.init(this)
    }
}