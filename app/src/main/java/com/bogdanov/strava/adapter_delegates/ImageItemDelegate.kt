package com.bogdanov.strava.adapter_delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.ItemImageBinding
import com.bogdanov.strava.models.ImageItem
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer

class ImageItemDelegate(
) : AbsListItemAdapterDelegate<Any, Any, ImageItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is ImageItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as ImageItem)
    }

    inner class ViewHolder(
        override val containerView: View,
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: ImageItem? = null

        private val binding: ItemImageBinding by viewBinding()

        fun bind(item: ImageItem) = with(containerView) {
            currentItem = item
            binding.title.text = item.title
            binding.author.text = item.author
        }
    }
}