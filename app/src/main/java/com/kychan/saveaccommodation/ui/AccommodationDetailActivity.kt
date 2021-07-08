package com.kychan.saveaccommodation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.iron.espresso.base.BaseActivity
import com.kychan.saveaccommodation.databinding.ActivityAccommodationDetailBinding
import com.kychan.saveaccommodation.ext.setImage
import com.kychan.saveaccommodation.ui.accommodation.AccommodationItem

class AccommodationDetailActivity : BaseActivity<ActivityAccommodationDetailBinding>({
    ActivityAccommodationDetailBinding.inflate(it)
}) {
    private val accommodationItem: AccommodationItem by lazy {
        intent.getSerializableExtra(KEY_ACCOMMODATION_ITEM) as AccommodationItem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setView()
    }

    private fun setView() {
        with(binding) {
            image.setImage(accommodationItem.description.imagePath)
            title.text = accommodationItem.title
            rate.rating = accommodationItem.rate
            subject.text = accommodationItem.description.subject
            price.text = accommodationItem.description.price.toString()
        }
    }

    companion object {
        private const val KEY_ACCOMMODATION_ITEM = "ACCOMMODATION_ITEM"
        fun getIntent(context: Context, item: AccommodationItem) =
            Intent(context, AccommodationDetailActivity::class.java)
                .putExtra(KEY_ACCOMMODATION_ITEM, item)
    }
}