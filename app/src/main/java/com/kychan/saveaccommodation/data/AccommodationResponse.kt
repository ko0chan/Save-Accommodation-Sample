package com.kychan.saveaccommodation.data

import com.google.gson.annotations.SerializedName

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
)

data class AccommodationProductDescriptionResponse(
    @SerializedName("imagePath")
    val imagePath: String? = null,
    @SerializedName("subject")
    val subject: String? = null,
    @SerializedName("price")
    val price: Int = -1,
)