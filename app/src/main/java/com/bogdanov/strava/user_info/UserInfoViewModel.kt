package com.bogdanov.strava.user_info

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.*
import com.bogdanov.strava.R
import com.bogdanov.strava.StravaApplication
import com.bogdanov.strava.datastore.DatastoreKeys
import com.bogdanov.strava.datastore.DatastoreRepository
import com.bogdanov.strava.datastore.SharedPrefs
import com.bogdanov.strava.db.UserRepository
import com.bogdanov.strava.models.User
import com.bogdanov.strava.network.NetworkRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class UserInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPrefs = SharedPrefs(application)

    private val repository = NetworkRepository()

    private val userRepository = UserRepository()

    private val userInfoLiveData = MutableLiveData<User?>(null)

    private val _failToast = MutableLiveData<Int>()

    private var currentInfoJob: Job? = null

    val userInfo: LiveData<User?>
        get() = userInfoLiveData

    val failToast: LiveData<Int>
        get() = _failToast

    fun getUserInfo(){
        currentInfoJob?.cancel()
        currentInfoJob = viewModelScope.launch {
            runCatching {
                repository.userInfo()
            }.onSuccess {
                userInfoLiveData.postValue(it)
                userRepository.saveUser(it)
                sharedPrefs.saveLong(it.id, "current_id")
            }.onFailure {
                try {
                    val id = sharedPrefs.currentId
                    val user = userRepository.getUserById(id)
                    userInfoLiveData.postValue(user)
                    _failToast.postValue(R.string.data_from_db)
                }catch (t: Throwable){
                    Timber.e(t)
                    _failToast.postValue(R.string.fail_load_data)
                }
            }
        }
    }
}