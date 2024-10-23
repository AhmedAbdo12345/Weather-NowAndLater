package com.example.weathernowandlater.di

import android.content.Context
import com.example.data.api.WeatherServices
import com.example.data.database.DataStoreManger
import com.example.data.datasource.local.LocalDataSource
import com.example.data.datasource.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun getDataStoreManger(@ApplicationContext context: Context) = DataStoreManger(context)

    @Provides
    fun getLocalDataSource(dataStoreManger: DataStoreManger) = LocalDataSource(dataStoreManger)


    @Provides
    fun getRemoteDataSource(weatherServices: WeatherServices) = RemoteDataSource(weatherServices)

}