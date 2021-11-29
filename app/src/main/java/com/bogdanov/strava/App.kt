package com.bogdanov.strava

import android.app.Application
import com.bogdanov.strava.datastore.SharedPrefs
import com.bogdanov.strava.data.local.Database
import com.bogdanov.strava.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
        SharedPrefs.init(this)
        Database.init(this)
    }
}