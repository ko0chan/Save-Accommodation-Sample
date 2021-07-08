package com.kychan.saveaccommodation.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kychan.saveaccommodation.R
import com.kychan.saveaccommodation.databinding.ItemBookmarkBinding
import com.kychan.saveaccommodation.ext.setImage

class BookmarkViewHolder(
    parent: ViewGroup,
    private val itemClick: (BookmarkItem) -> Unit,
    private val bookmarkClick: (BookmarkItem) -> Unit,
    private val binding: ItemBookmarkBinding =
        ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BookmarkItem) {
        with(binding) {
            root.setOnClickListener {
                itemClick(item)
            }
            bookmark.setOnClickListener {
                bookmarkClick(item)
            }
            image.setImage(item.thumbnail)
            title.text = item.title
            rating.rating = item.rate / 2
            bookmark.setImageResource(R.drawable.ic_bookmark)
            bookmarkDate.text = item.bookmarkDate
        }
    }
}