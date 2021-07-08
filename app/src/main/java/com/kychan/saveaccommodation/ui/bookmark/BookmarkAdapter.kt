package com.kychan.saveaccommodation.ui.bookmark

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class BookmarkAdapter(
    private val itemClick: (BookmarkItem) -> Unit,
    private val bookmarkClick: (BookmarkItem) -> Unit
) :
    ListAdapter<BookmarkItem, BookmarkViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder =
        BookmarkViewHolder(parent, itemClick, bookmarkClick)

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BookmarkItem>() {

            override fun areItemsTheSame(oldItem: BookmarkItem, newItem: BookmarkItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BookmarkItem, newItem: BookmarkItem): Boolean =
                oldItem == newItem
        }
    }
}