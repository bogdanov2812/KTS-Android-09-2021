package com.bogdanov.strava.datastore

import android.content.Context
import android.content.SharedPreferences

object SharedPrefs {

    private var sharedPrefs: SharedPreferences? = null

    var authToken: String?
        get() = sharedPrefs?.getString(AUTH_TOKEN, null)
        set(value) {
            sharedPrefs?.edit()
                ?.putString(AUTH_TOKEN, value)
                ?.apply()
        }

    var refreshToken: String?
        get() = sharedPrefs?.getString(REFRESH_TOKEN, null)
        set(value) {
            sharedPrefs?.edit()
                ?.putString(REFRESH_TOKEN, value)
                ?.apply()
        }

    var currentUserId: Long?
        get() = sharedPrefs?.getLong(CURRENT_USER_ID, 0L)
        set(value) {
            if (value != null) {
                sharedPrefs?.edit()
                    ?.putLong(CURRENT_USER_ID, value)
                    ?.apply()
            }
        }

    var onBoardingCheck: Boolean
        get() = sharedPrefs?.getBoolean(ON_BOARDING_CHECK, false) == true
        set(value) {
            sharedPrefs?.edit()
                ?.putBoolean(ON_BOARDING_CHECK, value)
                ?.apply()
        }

    fun init(context: Context){
        sharedPrefs = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    }


    private const val NAME = "app_preferences"
    private const val AUTH_TOKEN = "auth_token"
    private const val REFRESH_TOKEN = "refresh_token"
    private const val ON_BOARDING_CHECK = "on_boarding_check"
    private const val CURRENT_USER_ID = "current_user_id"

}