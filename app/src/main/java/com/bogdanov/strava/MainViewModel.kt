package com.bogdanov.strava

import androidx.lifecycle.ViewModel
import com.bogdanov.strava.models.TextItem
import java.util.*

class MainViewModel : ViewModel() {
    fun randomItems() = List(20) {
        val randomUUID = UUID.randomUUID()
        when ((1..2).random()) {
            1 -> TextItem(
                title = "Какой-то заголовок",
                author = "Какой-то автор",
                likes = (0..100).random(),
                uuid = randomUUID
            )
            else -> error("Wrong random number")
        }
    }
}