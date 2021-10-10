package com.bogdanov.strava

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.bogdanov.strava.adapter_delegates.ImageItemDelegate
import com.bogdanov.strava.adapter_delegates.PageLoadingAdapterDelegate
import com.bogdanov.strava.adapter_delegates.TextItemDelegate
import com.bogdanov.strava.models.ImageItem
import com.bogdanov.strava.models.TextItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MainListAdapter: AsyncListDifferDelegationAdapter<Any>(ComplexDiffCallback()) {

    init {
        delegatesManager
            .addDelegate(TextItemDelegate(::notifyItemChanged))
            .addDelegate(PageLoadingAdapterDelegate())
            .addDelegate(ImageItemDelegate())
    }

    class ComplexDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is TextItem -> first.uuid == (second as TextItem).uuid
                is ImageItem -> first.uuid == (second as ImageItem).uuid
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }
}