package com.kychan.saveaccommodation.ui.accommodation

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kychan.saveaccommodation.R
import com.kychan.saveaccommodation.databinding.ItemAccommodationBinding
import com.kychan.saveaccommodation.ext.setImage

class AccommodationViewHolder(
    parent: ViewGroup,
    private val itemClick: (AccommodationItem) -> Unit,
    private val bookmarkClick: (AccommodationItem) -> Unit,
    private val binding: ItemAccommodationBinding =
        ItemAccommodationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AccommodationItem) {
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
                item.isBookmark = !item.isBookmark
                setBookmarkImage(item.isBookmark)
            }
            image.setImage(item.thumbnail)
            title.text = item.title
            rating.text = rateSpannable

            setBookmarkImage(item.isBookmark)
        }
    }

    fun setBookmarkImage(isBookmark: Boolean) {
        if (isBookmark) {
            binding.bookmark.setImageResource(R.drawable.ic_bookmark)
        } else {
            binding.bookmark.setImageResource(R.drawable.ic_twotone_bookmark)
        }
    }
}