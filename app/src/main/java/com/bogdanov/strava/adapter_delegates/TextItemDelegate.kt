package com.bogdanov.strava.adapter_delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.ItemTextBinding
import com.bogdanov.strava.models.TextItem
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer

class TextItemDelegate (
    private val onLikeClick: (position: Int) -> Unit)
    : AbsListItemAdapterDelegate<Any, Any, TextItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is TextItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as TextItem)
    }

    inner class ViewHolder(
        private val viewBinding: ItemTextBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        private var currentItem: TextItem? = null


        fun bind(item: TextItem) = with(viewBinding) {
            currentItem = item
            viewBinding.imageButton.setOnClickListener {
                addLike(item)
            }
            viewBinding.title.text = item.title
            viewBinding.likes.text = item.likes.toString()
            viewBinding.author.text = item.author
        }

        fun addLike(item: TextItem){
            item.likes = item.likes + 1
            onLikeClick(layoutPosition)
        }
    }
}