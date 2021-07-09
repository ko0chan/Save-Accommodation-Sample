package com.kychan.saveaccommodation.data

import androidx.paging.PagingData
import androidx.paging.map
import com.kychan.saveaccommodation.data.local.AccommodationEntity
import com.kychan.saveaccommodation.data.local.AccommodationLocalDataSource
import com.kychan.saveaccommodation.data.remote.AccommodationRemoteDataSource
import com.kychan.saveaccommodation.ui.accommodation.AccommodationItem
import com.kychan.saveaccommodation.ui.bookmark.BookmarkItem
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class AccommodationRepository @Inject constructor(
    private val accommodationRemoteDataSource: AccommodationRemoteDataSource,
    private val accommodationLocalDataSource: AccommodationLocalDataSource
) {

    fun getAccommodationList(): Observable<PagingData<AccommodationItem>> {
        return accommodationRemoteDataSource.getAccommodationList()
            .map { pagingData ->
                pagingData.map {
                    val accommodationItem = it.toAccommodationItem()
                    Thread {
                        accommodationItem.isBookmark = accommodationLocalDataSource.getAccommodationId().contains(it.id)
                    }.start()
                    accommodationItem
                }
            }
    }

    fun fetchBookmarkList(): Observable<List<BookmarkItem>> {
        return accommodationLocalDataSource.getAccommodationAll()
            .map { list ->
                list.map {
                    it.toBookmarkItem()
                }
            }
    }

    fun insertAccommodation(accommodationEntity: AccommodationEntity): Completable {
        return accommodationLocalDataSource.insertAccommodation(accommodationEntity)
    }

    fun deleteAccommodation(id: Int): Completable {
        return accommodationLocalDataSource.deleteAccommodation(id)
    }
}
