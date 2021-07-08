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

    override fun onBindViewHolder(
        holder: AccommodationViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.contains(CHECK_ACTIVATE)) {
            val accommodationItem: AccommodationItem? = getItem(position)
            if (accommodationItem != null) {
                holder.setBookmarkImage(accommodationItem.isBookmark)
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    companion object {
        private const val CHECK_ACTIVATE = "check_activate"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AccommodationItem>() {
            override fun getChangePayload(oldItem: AccommodationItem, newItem: AccommodationItem): Any? {
                if (oldItem.isBookmark != newItem.isBookmark) return CHECK_ACTIVATE

                return super.getChangePayload(oldItem, newItem)
            }

            override fun areItemsTheSame(oldItem: AccommodationItem, newItem: AccommodationItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: AccommodationItem, newItem: AccommodationItem): Boolean =
                oldItem == newItem
        }
    }
}