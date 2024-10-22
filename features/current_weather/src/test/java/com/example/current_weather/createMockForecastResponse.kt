package com.example.current_weather

import com.example.data.models.remote.Clouds
import com.example.data.models.remote.Coord
import com.example.data.models.remote.CurrentResponse
import com.example.data.models.remote.Sys
import com.example.data.models.remote.WeatherItem
import com.example.data.models.remote.Wind

internal fun createMockCityWeatherResponse(): CurrentResponse {
    return CurrentResponse(
        id = 1,
        name = "Cairo",
        visibility = 10,
        timezone = 151515151,
        main = null,
        clouds = Clouds(450),
        sys = Sys(pod = null, country = "EG", sunset = 1729609917, sunrise = 1729609917),
        dt = 856,
        coord = Coord(lat = 31.545151, lon = 45.515444),
        weather = listOf(
            WeatherItem(id = 800, main = "Clear", description = "Clear Sky", icon = "01d")
        ),
        cod = 55,
        base = "",
        wind = Wind(deg = 11, gust = 12.5, speed = 8.5)
    )
}