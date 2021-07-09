package com.kychan.saveaccommodation.ui.accommodation

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class AccommodationAdapter(
    private val itemClick: (AccommodationItem) -> Unit,
    private val bookmarkClick: (AccommodationItem) -> Unit
) :
    PagingDataAdapter<AccommodationItem, AccommodationViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccommodationViewHolder =
        AccommodationViewHolder(parent, itemClick, bookmarkClick)

    override fun onBindViewHolder(holder: AccommodationViewHolder, position: Int) {
        val accommodationItem: AccommodationItem? = getItem(position)
        if (accommodationItem != null) {
            holder.bind(accommodationItem)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AccommodationItem>() {

            override fun areItemsTheSame(oldItem: AccommodationItem, newItem: AccommodationItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: AccommodationItem, newItem: AccommodationItem): Boolean =
                oldItem == newItem
        }
    }
}