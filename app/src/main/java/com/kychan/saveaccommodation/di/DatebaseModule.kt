package com.kychan.saveaccommodation.di

import android.content.Context
import androidx.room.Room
import com.kychan.saveaccommodation.data.local.AccommodationDatabase
import com.kychan.saveaccommodation.data.local.dao.AccommodationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideAccommodationDao(appDatabase: AccommodationDatabase): AccommodationDao {
        return appDatabase.accommodationDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AccommodationDatabase {
        return Room.databaseBuilder(
            appContext,
            AccommodationDatabase::class.java,
            "accommodation_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}