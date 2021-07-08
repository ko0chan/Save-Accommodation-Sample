package com.kychan.saveaccommodation.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kychan.saveaccommodation.data.local.AccommodationEntity

@Dao
interface AccommodationDao {
    @Query("SELECT * FROM accommodation_table")
    fun getAccommodationAll(): DataSource.Factory<Int, AccommodationEntity>

    @Query("SELECT id FROM accommodation_table")
    fun getAccommodationId(): List<Int>

    @Insert
    fun insertAccommodation(accommodationEntity: AccommodationEntity)

    @Query("DELETE FROM accommodation_table WHERE id = (:id)")
    fun deleteAccommodation(id: Int)

}