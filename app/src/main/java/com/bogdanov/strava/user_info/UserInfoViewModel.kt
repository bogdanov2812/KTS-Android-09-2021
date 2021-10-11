package com.bogdanov.strava.user_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bogdanov.strava.R
import com.bogdanov.strava.models.User
import com.bogdanov.strava.network.NetworkRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserInfoViewModel : ViewModel() {

    private val repository = NetworkRepository()

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
            }.onFailure {
                _failToast.postValue(R.string.fail_load_data)
                userInfoLiveData.postValue(null)
            }
        }
    }
}