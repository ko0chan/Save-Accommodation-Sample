package com.kychan.saveaccommodation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.kychan.saveaccommodation.data.AccommodationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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
}