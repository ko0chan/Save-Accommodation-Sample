package com.kychan.saveaccommodation.data.local

import androidx.paging.DataSource
import com.kychan.saveaccommodation.data.local.dao.AccommodationDao
import javax.inject.Inject

class AccommodationLocalDataSource @Inject constructor(
    private val accommodationDao: AccommodationDao
) {
    fun getAccommodationAll(): DataSource.Factory<Int, AccommodationEntity> {
        return accommodationDao.getAccommodationAll()
    }

    fun getAccommodationId(): List<Int> {
        return accommodationDao.getAccommodationId()
    }

    fun insertAccommodation(accommodationEntity: AccommodationEntity) {
        accommodationDao.insertAccommodation(accommodationEntity)
    }

    fun deleteAccommodation(id: Int) {
        accommodationDao.deleteAccommodation(id)
    }
}