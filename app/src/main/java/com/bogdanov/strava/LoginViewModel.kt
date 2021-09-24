package com.bogdanov.strava

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class LoginViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val mutableState = savedStateHandle.getLiveData("loginStatus", LoginParcelable(email = "", password = "", false))

    val state : LiveData<LoginParcelable> = mutableState

    fun updateStatus(email: String, password: String) {
        val buttonEnableStatus =
            validEmail(email) && validPassword(password)
        mutableState.value = LoginParcelable(email, password, buttonEnableStatus)
    }


    private fun validPassword(password: String): Boolean = password.length >= 8 && password.isNotEmpty()

    private fun validEmail(email:String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()

}