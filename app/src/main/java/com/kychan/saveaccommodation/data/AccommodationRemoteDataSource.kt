package com.kychan.saveaccommodation.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import com.kychan.saveaccommodation.api.AccommodationApi
import com.kychan.saveaccommodation.data.response.AccommodationProductResponse
import io.reactivex.Observable
import javax.inject.Inject

interface AccommodationRemoteDataSource {
    fun getAccommodationList(): Observable<PagingData<AccommodationProductResponse>>
}

class AccommodationRemoteDataSourceImpl @Inject constructor(private val accommodationApi: AccommodationApi) :
    AccommodationRemoteDataSource {
    override fun getAccommodationList(): Observable<PagingData<AccommodationProductResponse>> {
        return Pager(
            config = pagingConfig(),
            pagingSourceFactory = { AccommodationPagingSource(accommodationApi) }
        ).observable
    }

    companion object {
        private const val PAGE_SIZE = 20

        fun pagingConfig() = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true,
            initialLoadSize = PAGE_SIZE,
            prefetchDistance = 3
        )
    }
}