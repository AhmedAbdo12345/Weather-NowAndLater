package com.example.data.datasource.remote

import com.example.core.util.DataHolder
import com.example.data.models.remote.CurrentResponse
import com.example.data.models.remote.ForecastResponse

interface RemoteDataSourceInterface {
    suspend fun getCityWeather(city: String): DataHolder<CurrentResponse?>
    suspend fun getWeekForecast(city: String): DataHolder<ForecastResponse?>
}