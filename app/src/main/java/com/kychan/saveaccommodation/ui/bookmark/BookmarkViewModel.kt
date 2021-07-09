package com.kychan.saveaccommodation.ui.bookmark

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kychan.saveaccommodation.data.AccommodationRepository
import com.kychan.saveaccommodation.ui.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val accommodationRepository: AccommodationRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _bookmarkList = MutableLiveData<List<BookmarkItem>>()
    val bookmarkList: LiveData<List<BookmarkItem>>
        get() = _bookmarkList

    private var sortType: SortType = SortType.RECENT

    init {
        fetchBookmarkList()
    }

    fun setSortType(sortType: SortType) {
        this.sortType = sortType
        _bookmarkList.value = sortedByType(this.sortType, bookmarkList.value.orEmpty())
    }

    private fun fetchBookmarkList() {
        compositeDisposable.add(
            accommodationRepository
                .fetchBookmarkList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _bookmarkList.value = sortedByType(this.sortType, it)
                }, {
                    Log.e("BookmarkViewModel", it.toString())
                })
        )
    }

    private fun sortedByType(sortType: SortType, list: List<BookmarkItem>): List<BookmarkItem> {
        return when (sortType) {
            SortType.RECENT -> {
                list.sortedBy {
                    it.bookmarkDate
                }
            }
            SortType.RATING -> {
                list.sortedByDescending {
                    it.rate
                }
            }
        }
    }

    fun deleteAccommodation(id: Int) {
        compositeDisposable.add(
            accommodationRepository.deleteAccommodation(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },{
                    Log.e("BookmarkViewModel", it.toString())
                })
        )
    }
}