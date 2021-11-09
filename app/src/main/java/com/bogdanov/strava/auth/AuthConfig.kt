package com.bogdanov.strava.auth

import net.openid.appauth.ResponseTypeValues

object AuthConfig {
    const val AUTH_URI = "https://www.strava.com/oauth/authorize"
    const val TOKEN_URI = "https://www.strava.com/oauth/token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "profile:read_all,activity:read_all,profile:write,activity:write"

    const val CLIENT_ID = "72128"
    const val CLIENT_SECRET = "f8fe54eb5912269d74e97e201f00ccf51917f674"
    const val CALLBACK_URL = "strava://strava.auth/callback"
}