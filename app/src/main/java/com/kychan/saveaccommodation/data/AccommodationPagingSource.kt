package com.kychan.saveaccommodation.data

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.kychan.saveaccommodation.api.AccommodationApi
import com.kychan.saveaccommodation.data.response.AccommodationProductResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

class AccommodationPagingSource(
    private val accommodationApi: AccommodationApi
) : RxPagingSource<Int, AccommodationProductResponse>() {

    override fun getRefreshKey(state: PagingState<Int, AccommodationProductResponse>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, AccommodationProductResponse>> {
        val position = params.key ?: 1

        return accommodationApi.getAccommodationList(position)
            .subscribeOn(Schedulers.io())
            .map<LoadResult<Int, AccommodationProductResponse>> { result ->
                LoadResult.Page(
                    data = result.data?.product.orEmpty(),
                    prevKey = null,
                    nextKey = position + 1
                )
            }
            .onErrorReturn { e ->
                when (e) {
                    is IOException -> LoadResult.Error(e)
                    is HttpException -> LoadResult.Error(e)
                    else -> throw e
                }
            }
    }
}