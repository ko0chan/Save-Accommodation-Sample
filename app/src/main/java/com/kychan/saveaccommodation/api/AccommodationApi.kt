package com.kychan.saveaccommodation.api

import com.kychan.saveaccommodation.data.AccommodationResponse
import com.kychan.saveaccommodation.data.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AccommodationApi {
    @GET("App/json/{page}.json")
    fun getSearchImage(
        @Path("page") page: Int,
    ): Single<BaseResponse<AccommodationResponse>>
}