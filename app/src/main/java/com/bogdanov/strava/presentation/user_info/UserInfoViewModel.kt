package com.bogdanov.strava.presentation.user_info

import androidx.lifecycle.*
import com.bogdanov.strava.R
import com.bogdanov.strava.data.remote.auth.AuthRepository
import com.bogdanov.strava.utils.Resource
import com.bogdanov.strava.domain.use_case.GetUserInfoUseCase
import com.bogdanov.strava.datastore.SharedPrefs
import com.bogdanov.strava.domain.model.User
import com.bogdanov.strava.domain.repository.UserRepository
import com.bogdanov.strava.domain.use_case.UpdateUserInfoUserCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserInfoViewModel(
    private val getUserUseCase: GetUserInfoUseCase,
    private val updateUserInfoUserCase: UpdateUserInfoUserCase,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {

    private val _actionToast = MutableLiveData<Int>()
    val actionToast: LiveData<Int> = _actionToast

    private val _buttonsEnable = MutableStateFlow(false)
    val buttonsEnable: StateFlow<Boolean> = _buttonsEnable

    private val _userInfo = MutableStateFlow<User?>(null)
    val userInfo: StateFlow<User?> = _userInfo


    fun deauthorize(){
        authRepository.deauthorize()
    }

    fun updateUserInfo(weight: Float) {
        viewModelScope.launch {
            updateUserInfoUserCase.execute(weight)
            getUserInfo()
        }
    }

    fun clearLocalData(){
        viewModelScope.launch {
            userRepository.deleteAllUsers()
            with(sharedPrefs){
                authToken = null
                refreshToken = null
                currentUserId = null
            }
        }

    }

    fun getUserInfo(){
        viewModelScope.launch {

            when(val user = getUserUseCase.execute()){
                is Resource.SuccessFromApi ->{
                    _userInfo.emit(user.data)
                    _actionToast.postValue(R.string.data_from_api)
                    _buttonsEnable.emit(true)
                }
                is Resource.SuccessFromDb ->{
                    _userInfo.emit(user.data)
                    _actionToast.postValue(R.string.data_from_db)
                    _buttonsEnable.emit(false)
                }
                is Resource.Loading ->{
                    _buttonsEnable.emit(false)
                }
                is Resource.Error ->{
                    _buttonsEnable.emit(false)
                }
            }
        }
    }
}