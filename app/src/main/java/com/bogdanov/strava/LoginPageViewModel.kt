package com.bogdanov.strava

import android.util.Patterns
import androidx.lifecycle.*

class LoginPageViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val mutableState = savedStateHandle.getLiveData("loginStatus", LoginParcelable(email = "", password = "", false))
    val state : LiveData<LoginParcelable> = mutableState

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage : LiveData<String?> = _errorMessage

    fun updateStatus(email: String, password: String) {
        val buttonEnableStatus =
            validEmail(email) && validPassword(password)
        mutableState.value = LoginParcelable(email, password, buttonEnableStatus)
    }

    fun setError(password: String, errorText: String){
        if (validPassword(password)){
            _errorMessage.value = null
        }
        else{
            _errorMessage.value = errorText
        }
    }

    private fun validPassword(password: String): Boolean = password.length >= 8 && password.isNotEmpty()

    private fun validEmail(email:String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
}