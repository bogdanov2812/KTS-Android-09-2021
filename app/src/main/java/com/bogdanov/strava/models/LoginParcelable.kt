package com.bogdanov.strava.models

import android.os.Message
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginParcelable(
    val email: String,
    val password: String,
    val buttonEnableStatus : Boolean
) : Parcelable
