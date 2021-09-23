package com.bogdanov.strava

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {


    val email: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val login: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun saveEmail(emailText: String){
        email.value = emailText
    }

    fun savePassword(passwordText: String){
        password.value = passwordText
    }


    fun editEmail(newText: String): Boolean{
        return newText != email.value
    }

    fun editPassword(newText: String): Boolean{
        return newText != password.value
    }

    fun validPassword(password: String): Boolean = password.length >= 8 && password.isNotEmpty()

    fun validEmail(email:String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()


    fun loginVisible(email: String, password: String){
        login.value = validEmail(email) && validPassword(password)
    }

}