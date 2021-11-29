package com.bogdanov.strava.data.remote.auth

import android.net.Uri
import com.bogdanov.strava.data.local.entity.UserEntity
import com.bogdanov.strava.data.remote.dto.UserDto
import com.bogdanov.strava.datastore.SharedPrefs
import com.google.gson.Gson
import net.openid.appauth.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.IOException

class AuthRepository() {

    fun deauthorize(){
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor {
                Timber.tag("Network").d(it)
            }
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()

        Timber.d(SharedPrefs.authToken)

        val requestBody = FormBody.Builder()
            .add("access_token", SharedPrefs.authToken ?: "")
            .build()
        val request = Request.Builder()
            .url("https://www.strava.com/oauth/deauthorize")
            .post(requestBody)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Timber.e(e)
            }

            override fun onResponse(call: Call, response: Response) {
                Timber.d("${response.code}")
            }
        })
    }


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
        onComplete: (user: UserEntity) -> Unit,
        onError: () -> Unit
    ) {
        authService.performTokenRequest(tokenRequest, getClientAuthentication()) { response, ex ->
            when {
                response != null -> {
                    val accessToken = response.accessToken.orEmpty()

                    val refreshToken = response.refreshToken.orEmpty()

                    SharedPrefs.authToken = accessToken
                    SharedPrefs.refreshToken = refreshToken

                    val user = Gson().fromJson(response.additionalParameters["athlete"], UserDto::class.java)

                    Timber.d("$user")

                    val userId = user.id

                    SharedPrefs.currentUserId = userId
                    onComplete(user.toEntity())
                }
                else -> onError()
            }
        }
    }


    private fun getClientAuthentication(): ClientAuthentication {
        return ClientSecretPost(AuthConfig.CLIENT_SECRET)
    }

}