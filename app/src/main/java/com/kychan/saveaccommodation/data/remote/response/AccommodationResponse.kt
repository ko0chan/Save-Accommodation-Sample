package com.kychan.saveaccommodation.data.remote.response

import com.google.gson.annotations.SerializedName
import com.kychan.saveaccommodation.ui.accommodation.AccommodationDescriptionItem
import com.kychan.saveaccommodation.ui.accommodation.AccommodationItem

data class AccommodationResponse(
    @SerializedName("totalCount")
    val totalCount: Int = -1,
    @SerializedName("product")
    val product: List<AccommodationProductResponse>? = null
)

data class AccommodationProductResponse(
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("description")
    val description: AccommodationProductDescriptionResponse? = null,
    @SerializedName("rate")
    val rate: Float = -1f,
) {
    fun toAccommodationItem(): AccommodationItem =
        AccommodationItem(
            id = id,
            thumbnail = thumbnail.orEmpty(),
            title = name.orEmpty(),
            rate = rate,
            description = AccommodationDescriptionItem(
                imagePath = description?.imagePath.orEmpty(),
                subject = description?.subject.orEmpty(),
                price = description?.price ?: -1
            ),
            isBookmark = false
        )
}

data class AccommodationProductDescriptionResponse(
    @SerializedName("imagePath")
    val imagePath: String? = null,
    @SerializedName("subject")
    val subject: String? = null,
    @SerializedName("price")
    val price: Int = -1,
)