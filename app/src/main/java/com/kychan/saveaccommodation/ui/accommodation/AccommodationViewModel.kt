package com.kychan.saveaccommodation.ui.accommodation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.kychan.saveaccommodation.data.AccommodationRepository
import com.kychan.saveaccommodation.data.local.AccommodationEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class AccommodationViewModel @Inject constructor(
    private val accommodationRepository: AccommodationRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _accommodationList = MutableLiveData<PagingData<AccommodationItem>>()
    val accommodationList: LiveData<PagingData<AccommodationItem>>
        get() = _accommodationList

    init {
        fetchAccommodationList()
    }

    fun fetchAccommodationList() {
        compositeDisposable.add(
            accommodationRepository
                .getAccommodationList()
                .cachedIn(viewModelScope)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _accommodationList.value = it
                    Log.d("AccommodationViewModel", it.toString())
                }, {
                    Log.d("AccommodationViewModel", it.toString())
                })
        )
    }

    fun insertAccommodation(item: AccommodationItem) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        compositeDisposable.add(
            accommodationRepository.insertAccommodation(
                AccommodationEntity.of(item, dateFormat.format(System.currentTimeMillis()))
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    fun deleteAccommodation(id: Int) {
        accommodationRepository.deleteAccommodation(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}