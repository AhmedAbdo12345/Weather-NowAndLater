package com.example.data.api

import com.example.data.BuildConfig
import com.example.data.models.remote.CurrentResponse
import com.example.data.models.remote.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServices {
    @GET(ApiConstants.API_PATH_WEATHER)
     fun getCityWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Call<CurrentResponse?>

    @GET(ApiConstants.API_PATH_FORECAST)
     fun getWeekForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Call<ForecastResponse?>
}