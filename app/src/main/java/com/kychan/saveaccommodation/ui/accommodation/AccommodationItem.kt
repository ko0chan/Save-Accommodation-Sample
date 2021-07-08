package com.kychan.saveaccommodation.ui.accommodation

import java.io.Serializable

data class AccommodationItem(
    val id: Int,
    val thumbnail: String,
    val title: String,
    val rate: Float,
    val description: AccommodationDescriptionItem,
    var isBookmark: Boolean
) : Serializable

data class AccommodationDescriptionItem(
    val imagePath: String,
    val subject: String,
    val price: Int,
) : Serializable