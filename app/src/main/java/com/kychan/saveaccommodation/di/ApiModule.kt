package com.kychan.saveaccommodation.di

import com.kychan.saveaccommodation.api.AccommodationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val URL = "https://www.gccompany.co.kr/"

    @Singleton
    @Provides
    fun provideAccommodationApi(): AccommodationApi {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AccommodationApi::class.java)
    }
}