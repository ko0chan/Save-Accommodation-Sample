package com.kychan.saveaccommodation.ui.bookmark

data class BookmarkItem(
    val id: Int,
    val thumbnail: String,
    val title: String,
    val rate: Float,
    val description: BookmarkDescriptionItem,
    val bookmarkDate: String
)

data class BookmarkDescriptionItem(
    val imagePath: String,
    val subject: String,
    val price: Int,
)