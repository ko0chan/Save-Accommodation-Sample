package com.kychan.saveaccommodation.ui.bookmark

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
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
            val rateSpannable = SpannableStringBuilder("★ ${item.rate}")
            rateSpannable.setSpan(
                ForegroundColorSpan(Color.GREEN),
                0,
                1,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )

            root.setOnClickListener {
                itemClick(item)
            }
            bookmark.setOnClickListener {
                bookmarkClick(item)
            }
            image.setImage(item.thumbnail)
            title.text = item.title
            rating.text = rateSpannable
            bookmark.setImageResource(R.drawable.ic_bookmark)
            bookmarkDate.text = item.bookmarkDate

        }
    }
}