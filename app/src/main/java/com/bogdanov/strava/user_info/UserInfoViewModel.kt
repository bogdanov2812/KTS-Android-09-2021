package com.bogdanov.strava.user_info

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.*
import com.bogdanov.strava.R
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

class UserInfoViewModel(
    private val repository: NetworkRepository,
    private val userRepository: UserRepository
) : ViewModel() {

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
                if (it != userRepository.getUserById(it.id)){
                    Timber.d("SUCCESS", "New user info:$it")
                    userRepository.saveUser(it)
                }
            }.onFailure {
                try {
                    val user = SharedPrefs.currentUserId?.let { it1 ->
                        userRepository.getUserById(
                            it1
                        )
                    }
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