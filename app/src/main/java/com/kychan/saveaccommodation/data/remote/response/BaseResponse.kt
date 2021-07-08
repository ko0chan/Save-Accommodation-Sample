package com.kychan.saveaccommodation.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("msg") val message: String,
    @SerializedName("data") val data: T? = null,
    @SerializedName("code") val code: Int? = null
)