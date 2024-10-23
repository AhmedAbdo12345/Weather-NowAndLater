package com.example.weathernowandlater.di

import com.example.data.datasource.local.LocalDataSource
import com.example.data.datasource.remote.RemoteDataSource
import com.example.data.repository.WeatherRepository
import com.example.data.repository.WeatherRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideWeatherRepository(
        localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource
    ): WeatherRepository {
        return WeatherRepositoryImplementation(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

}