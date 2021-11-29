package com.bogdanov.strava.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class SuccessFromApi<T>(data: T): Resource<T>(data)
    class SuccessFromDb<T>(data: T): Resource<T>(data)
    class Error<T>(data: T? = null, message: String): Resource<T>(data, message = message)
    class Loading<T>(data: T? = null): Resource<T>(data)

}