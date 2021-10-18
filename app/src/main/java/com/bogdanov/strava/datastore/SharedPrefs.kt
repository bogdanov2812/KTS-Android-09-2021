package com.bogdanov.strava.datastore

import android.content.Context

class SharedPrefs(context: Context) {

    private val sharedPrefs = context.getSharedPreferences("Auth", Context.MODE_PRIVATE)

    val token = sharedPrefs.getString("token", null)

    val refreshToken = sharedPrefs.getString("token_refresh", null)

    val currentId = sharedPrefs.getLong("current_id", 0L)

    fun saveString(value: String, key: String){
        sharedPrefs.edit()
            .putString(key, value)
            .apply()
    }

    fun saveLong(value: Long, key: String){
        sharedPrefs.edit()
            .putLong(key, value)
            .apply()
    }
}