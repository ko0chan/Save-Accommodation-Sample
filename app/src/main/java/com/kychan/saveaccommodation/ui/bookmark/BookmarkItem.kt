package com.kychan.saveaccommodation.ui.bookmark

import com.kychan.saveaccommodation.ui.accommodation.AccommodationDescriptionItem
import com.kychan.saveaccommodation.ui.accommodation.AccommodationItem

data class BookmarkItem(
    val id: Int,
    val thumbnail: String,
    val title: String,
    val rate: Float,
    val description: BookmarkDescriptionItem,
    val bookmarkDate: String
) {
    fun toAccommodationItem(): AccommodationItem =
        AccommodationItem(
            id = id,
            thumbnail = thumbnail,
            title = title,
            rate = rate,
            description = AccommodationDescriptionItem(
                imagePath = description.imagePath,
                subject = description.subject,
                price = description.price
            ),
            isBookmark = true
        )
}

data class BookmarkDescriptionItem(
    val imagePath: String,
    val subject: String,
    val price: Int,
)