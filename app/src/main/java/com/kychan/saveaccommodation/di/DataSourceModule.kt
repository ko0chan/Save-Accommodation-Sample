package com.kychan.saveaccommodation.di

import com.kychan.saveaccommodation.data.remote.AccommodationRemoteDataSource
import com.kychan.saveaccommodation.data.remote.AccommodationRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindAccommodationRemoteDataSource(
        accommodationRemoteDataSourceImpl: AccommodationRemoteDataSourceImpl
    ): AccommodationRemoteDataSource
}