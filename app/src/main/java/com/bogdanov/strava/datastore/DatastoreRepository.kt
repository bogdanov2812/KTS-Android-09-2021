package com.bogdanov.strava.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class DatastoreRepository(context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    private val dataStore: DataStore<Preferences> = context.dataStore


    suspend fun saveString(text: String, key: Preferences.Key<String>){
        dataStore.edit {
            it[key] = text
        }
    }

    suspend fun saveLong(text: Long, key: Preferences.Key<Long>){
        dataStore.edit {
            it[key] = text
        }
    }


    fun getValues(): Flow<Preferences> {
        return dataStore.data
    }

    companion object{
        private const val DATASTORE_NAME = "datastore"
    }
}