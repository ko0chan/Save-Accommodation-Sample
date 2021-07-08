package com.kychan.saveaccommodation.ui

data class AccommodationItem(
    val id: Int,
    val thumbnail: String,
    val title: String,
    val rate: Float,
    val description: AccommodationDescriptionItem,
    var isBookmark: Boolean
)

data class AccommodationDescriptionItem(
    val imagePath: String,
    val subject: String,
    val price: Int,
)