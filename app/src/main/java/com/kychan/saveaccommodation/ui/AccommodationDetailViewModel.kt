package com.kychan.saveaccommodation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kychan.saveaccommodation.data.AccommodationRepository
import com.kychan.saveaccommodation.data.local.AccommodationEntity
import com.kychan.saveaccommodation.ui.accommodation.AccommodationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class AccommodationDetailViewModel @Inject constructor(
    private val accommodationRepository: AccommodationRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun insertAccommodation(item: AccommodationItem) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        compositeDisposable.add(
            accommodationRepository.insertAccommodation(
                AccommodationEntity.of(item, dateFormat.format(System.currentTimeMillis()))
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },{
                    Log.e("AccDetailViewModel", it.toString())
                })
        )
    }

    fun deleteAccommodation(id: Int) {
        compositeDisposable.add(
            accommodationRepository.deleteAccommodation(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },{
                    Log.e("AccDetailViewModel", it.toString())
                })
        )
    }
}