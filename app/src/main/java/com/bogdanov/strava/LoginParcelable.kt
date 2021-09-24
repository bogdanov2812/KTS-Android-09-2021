package com.bogdanov.strava

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class LoginParcelable(
    val email: String,
    val password: String,
    val buttonEnableStatus : Boolean
):Parcelable
