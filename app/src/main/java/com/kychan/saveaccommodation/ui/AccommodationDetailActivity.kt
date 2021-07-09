package com.kychan.saveaccommodation.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.activity.viewModels
import com.kychan.saveaccommodation.R
import com.kychan.saveaccommodation.base.BaseActivity
import com.kychan.saveaccommodation.databinding.ActivityAccommodationDetailBinding
import com.kychan.saveaccommodation.ext.setImage
import com.kychan.saveaccommodation.ui.accommodation.AccommodationItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccommodationDetailActivity : BaseActivity<ActivityAccommodationDetailBinding>({
    ActivityAccommodationDetailBinding.inflate(it)
}) {
    private val accommodationDetailViewModel: AccommodationDetailViewModel by viewModels()

    private val accommodationItem: AccommodationItem by lazy {
        intent.getSerializableExtra(KEY_ACCOMMODATION_ITEM) as AccommodationItem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setView()
    }

    private fun setView() {
        with(binding) {
            val rateSpannable = SpannableStringBuilder("★ ${accommodationItem.rate}")
            rateSpannable.setSpan(
                ForegroundColorSpan(Color.GREEN),
                0,
                1,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )

            image.setImage(accommodationItem.description.imagePath)
            title.text = accommodationItem.title
            rate.text = rateSpannable
            subject.text = accommodationItem.description.subject
            price.text = getString(R.string.price_won, accommodationItem.description.price)
            setBookmarkImage(accommodationItem.isBookmark)

            bookmark.setOnClickListener {
                if (accommodationItem.isBookmark) {
                    accommodationDetailViewModel.deleteAccommodation(accommodationItem.id)
                } else {
                    accommodationDetailViewModel.insertAccommodation(accommodationItem)
                }
                accommodationItem.isBookmark = !accommodationItem.isBookmark
                setBookmarkImage(accommodationItem.isBookmark)
            }
        }
    }

    private fun setBookmarkImage(isBookmark: Boolean) {
        if (isBookmark) {
            binding.bookmark.setImageResource(R.drawable.ic_bookmark)
        } else {
            binding.bookmark.setImageResource(R.drawable.ic_twotone_bookmark)
        }
    }

    companion object {
        private const val KEY_ACCOMMODATION_ITEM = "ACCOMMODATION_ITEM"
        fun getIntent(context: Context, item: AccommodationItem) =
            Intent(context, AccommodationDetailActivity::class.java)
                .putExtra(KEY_ACCOMMODATION_ITEM, item)
    }
}