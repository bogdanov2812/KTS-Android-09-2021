package com.bogdanov.strava.adapter_delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.ItemLoadingPageBinding
import com.bogdanov.strava.models.LoadingItem
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer

class PageLoadingAdapterDelegate :
    AbsListItemAdapterDelegate<LoadingItem, Any, PageLoadingAdapterDelegate.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(ItemLoadingPageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is LoadingItem
    }

    override fun onBindViewHolder(
        item: LoadingItem,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
    }

    class Holder(
        private val viewBinding: ItemLoadingPageBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)
}