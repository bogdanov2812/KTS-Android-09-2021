package com.bogdanov.strava.auth

import android.net.Uri
import net.openid.appauth.*

class AuthRepository {
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
                    AuthConfig.token = accessToken
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