package com.example.data.datasource.remote

import com.example.core.extensions.asDataHolderResponse
import com.example.core.util.DataHolder
import com.example.data.api.WeatherServices
import com.example.data.models.remote.CurrentResponse
import com.example.data.models.remote.ForecastResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val weatherServices: WeatherServices):RemoteDataSourceInterface{

   override suspend fun getCityWeather(city: String): DataHolder<CurrentResponse?> {
        return weatherServices.getCityWeather(city).asDataHolderResponse()
    }

   override suspend fun getWeekForecast(city: String): DataHolder<ForecastResponse?> {
        return weatherServices.getWeekForecast(city = city).asDataHolderResponse()
    }
}
