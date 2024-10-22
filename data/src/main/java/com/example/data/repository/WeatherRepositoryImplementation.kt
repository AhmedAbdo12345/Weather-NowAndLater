package com.example.data.repository

import com.example.core.util.DataHolder
import com.example.data.datasource.local.LocalDataSourceInterface
import com.example.data.datasource.remote.RemoteDataSourceInterface
import com.example.data.models.remote.CurrentResponse
import com.example.data.models.remote.ForecastResponse
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(
    private val localDataSource: LocalDataSourceInterface,
    private val remoteDataSource: RemoteDataSourceInterface
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
