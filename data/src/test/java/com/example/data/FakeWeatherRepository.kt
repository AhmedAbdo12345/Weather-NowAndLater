package com.example.data

import com.example.core.util.DataHolder
import com.example.data.models.remote.CurrentResponse
import com.example.data.models.remote.ForecastResponse
import com.example.data.repository.WeatherRepository

class FakeWeatherRepository:WeatherRepository {
    private var cityName: String? = null

    override suspend fun saveCity(city: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getCity(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun getCityWeather(city: String): DataHolder<CurrentResponse?> {
        TODO("Not yet implemented")
    }

    override suspend fun getWeekForecast(city: String): DataHolder<ForecastResponse?> {
        TODO("Not yet implemented")
    }
}