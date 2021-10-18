package com.bogdanov.strava.user_workouts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bogdanov.strava.databinding.ItemWorkoutBinding
import com.bogdanov.strava.models.Workout
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import java.text.SimpleDateFormat
import java.util.*

class WorkoutDelegate: AbsListItemAdapterDelegate<Workout, Workout, WorkoutDelegate.ViewHolder>(){

    override fun isForViewType(
        item: Workout,
        items: MutableList<Workout>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(ItemWorkoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(
        item: Workout,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    inner class ViewHolder(
        private val viewBinding: ItemWorkoutBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(item: Workout) = with(viewBinding) {
            name.text = item.name
            date.text = reformatDate(item.start_date)
            timeTrain.text = reformatTime(item.start_date)
            distance.text = item.distance.toString()
            temp.text = "%.2f".format(item.average_speed)
            time.text = timeToMinute(item.moving_time).toString()
        }

        private fun reformatDate(dateTime: String): String {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)

            return dateFormat.format(parser.parse(dateTime))
        }

        private fun reformatTime(dateTime: String): String{
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
            val timeFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)

            return timeFormat.format(parser.parse(dateTime))
        }

        private fun timeToMinute(seconds: Int): Int {
            return seconds / 60
        }
    }
}