package com.example.data

import com.example.core.util.DataHolder
import com.example.data.datasource.remote.RemoteDataSourceInterface
import com.example.data.models.remote.CurrentResponse
import com.example.data.models.remote.ForecastResponse

class FakeRemoteDataSource : RemoteDataSourceInterface {

    private var currentResponse: CurrentResponse? = null
    private var forecastResponse: ForecastResponse? = null

    fun setCityWeatherResponse(response: CurrentResponse?) {
        currentResponse = response
    }

    fun setWeekForecastResponse(response: ForecastResponse?) {
        forecastResponse = response
    }

    override suspend fun getCityWeather(city: String): DataHolder<CurrentResponse?> {
        return if (currentResponse != null) {
            DataHolder.Success(currentResponse)
        } else {
            DataHolder.Fail(Exception("City weather not available"))
        }
    }

    override suspend fun getWeekForecast(city: String): DataHolder<ForecastResponse?> {
        return if (forecastResponse != null) {
            DataHolder.Success(forecastResponse)
        } else {
            DataHolder.Fail(Exception("Weekly Forecast not available"))
        }

    }
}