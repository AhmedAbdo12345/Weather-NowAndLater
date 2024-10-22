package com.example.data.repository

import com.example.core.util.DataHolder
import com.example.data.datasource.remote.RemoteDataSource
import com.example.data.datasource.local.LocalDataSource
import com.example.data.models.remote.CurrentResponse
import com.example.data.models.remote.ForecastResponse
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {

    override suspend fun getCityWeather(city: String): DataHolder<CurrentResponse?> {

        return remoteDataSource.getCityWeather(city)
    }

    override suspend fun getWeekForecast(city: String): DataHolder<ForecastResponse?> {
        return remoteDataSource.getWeekForecast(city)
    }

    override suspend fun saveCity(city: String): Boolean {
        return localDataSource.saveCity(city)
    }

    override suspend fun getCity(): String? {
        return localDataSource.getCity()
    }
}
