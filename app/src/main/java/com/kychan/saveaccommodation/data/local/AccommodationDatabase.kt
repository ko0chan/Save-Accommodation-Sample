package com.kychan.saveaccommodation.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kychan.saveaccommodation.data.local.dao.AccommodationDao

@Database(entities = [AccommodationEntity::class], version = 1)
abstract class AccommodationDatabase : RoomDatabase() {

    abstract fun accommodationDao(): AccommodationDao
}