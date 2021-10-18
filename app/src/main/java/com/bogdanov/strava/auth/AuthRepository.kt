package com.bogdanov.strava.auth

import android.content.Context
import android.net.Uri
import com.bogdanov.strava.datastore.DatastoreRepository
import com.bogdanov.strava.datastore.SharedPrefs
import net.openid.appauth.*

class AuthRepository(context: Context) {

    private val sharedPrefs = SharedPrefs(context)

    fun getAuthRequest(): AuthorizationRequest {
        val serviceConfiguration = AuthorizationServiceConfiguration(
            Uri.parse(AuthConfig.AUTH_URI),
            Uri.parse(AuthConfig.TOKEN_URI)
        )

        val redirectUri = Uri.parse(AuthConfig.CALLBACK_URL)

        return AuthorizationRequest.Builder(
            serviceConfiguration,
            AuthConfig.CLIENT_ID,
            AuthConfig.RESPONSE_TYPE,
            redirectUri
        )
            .setScope(AuthConfig.SCOPE)
            .build()
    }

    fun performTokenRequest(
        authService: AuthorizationService,
        tokenRequest: TokenRequest,
        onComplete: () -> Unit,
        onError: () -> Unit
    ) {
        authService.performTokenRequest(tokenRequest, getClientAuthentication()) { response, ex ->
            when {
                response != null -> {
                    val accessToken = response.accessToken.orEmpty()

                    val refreshToken = response.refreshToken.orEmpty()

                    sharedPrefs.saveString(accessToken, "token")
                    sharedPrefs.saveString(refreshToken, "refresh_token")

                    onComplete()
                }
                else -> onError()
            }
        }
    }


    private fun getClientAuthentication(): ClientAuthentication {
        return ClientSecretPost(AuthConfig.CLIENT_SECRET)
    }

}