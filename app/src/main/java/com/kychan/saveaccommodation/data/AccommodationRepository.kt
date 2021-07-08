package com.kychan.saveaccommodation.data

import androidx.paging.PagingData
import androidx.paging.map
import com.kychan.saveaccommodation.ui.AccommodationItem
import io.reactivex.Observable
import javax.inject.Inject

class AccommodationRepository @Inject constructor(
    private val accommodationRemoteDataSource: AccommodationRemoteDataSource
) {

    fun getAccommodationList(): Observable<PagingData<AccommodationItem>> {
        return accommodationRemoteDataSource.getAccommodationList()
            .map { pagingData ->
                pagingData.map {
                    it.toAccommodationItem()
                }
            }
    }
}
