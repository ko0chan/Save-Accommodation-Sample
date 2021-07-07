package com.kychan.saveaccommodation.ui

data class AccommodationItem(
    val id: Int,
    val thumbnail: String,
    val title: String,
    val rate: Float,
    var isBookmark: Boolean
)