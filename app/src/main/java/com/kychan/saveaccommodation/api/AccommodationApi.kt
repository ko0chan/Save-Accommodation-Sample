package com.kychan.saveaccommodation.api

import com.kychan.saveaccommodation.data.response.AccommodationResponse
import com.kychan.saveaccommodation.data.response.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AccommodationApi {
    @GET("App/json/{page}.json")
    fun getAccommodationList(
        @Path("page") page: Int,
    ): Single<BaseResponse<AccommodationResponse>>
}