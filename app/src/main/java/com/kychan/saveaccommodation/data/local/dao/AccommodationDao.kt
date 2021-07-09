package com.kychan.saveaccommodation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kychan.saveaccommodation.data.local.AccommodationEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface AccommodationDao {
    @Query("SELECT * FROM accommodation_table")
    fun getAccommodationAll(): Observable<List<AccommodationEntity>>

    @Query("SELECT id FROM accommodation_table")
    fun getAccommodationId(): List<Int>

    @Insert
    fun insertAccommodation(accommodationEntity: AccommodationEntity): Completable

    @Query("DELETE FROM accommodation_table WHERE id = (:id)")
    fun deleteAccommodation(id: Int): Completable

}