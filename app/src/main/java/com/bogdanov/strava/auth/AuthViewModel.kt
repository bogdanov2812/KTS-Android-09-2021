package com.bogdanov.strava.auth

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bogdanov.strava.R
import com.bogdanov.strava.utils.SingleLiveEvent
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest
import timber.log.Timber

class AuthViewModel(application: Application) : AndroidViewModel(application){
    private val authRepository = AuthRepository()
    private val authService: AuthorizationService = AuthorizationService(getApplication())
    private val openAuthPageLiveEvent = SingleLiveEvent<Intent>()
    private val authSuccessLiveEvent = SingleLiveEvent<Unit>()
    private val loadingMutableLiveData = MutableLiveData(false)
    private val toastLiveEvent = SingleLiveEvent<Int>()



    val openAuthPageLiveData: LiveData<Intent>
        get() = openAuthPageLiveEvent

    val loadingLiveData: LiveData<Boolean>
        get() = loadingMutableLiveData

    val authSuccessLiveData: LiveData<Unit>
        get() = authSuccessLiveEvent

    val toastLiveData: LiveData<Int>
        get() = toastLiveEvent

    fun onAuthCodeFailed(exception: AuthorizationException) {
        toastLiveEvent.postValue(R.string.auth_canceled)
    }

    fun onAuthCodeReceived(tokenRequest: TokenRequest) {
        authRepository.performTokenRequest(
            authService = authService,
            tokenRequest = tokenRequest,
            onComplete = {
                println("SUCCES")
                loadingMutableLiveData.postValue(false)
                authSuccessLiveEvent.postValue(Unit)
            },
            onError = {
                println("ERROR")
                loadingMutableLiveData.postValue(false)
                toastLiveEvent.postValue(R.string.auth_canceled)
            }
        )
    }

    fun openLoginPage() {
        val openAuthPageIntent = authService.getAuthorizationRequestIntent(
            authRepository.getAuthRequest()
        )

        openAuthPageLiveEvent.postValue(openAuthPageIntent)
    }

    override fun onCleared() {
        super.onCleared()
        authService.dispose()
    }
}