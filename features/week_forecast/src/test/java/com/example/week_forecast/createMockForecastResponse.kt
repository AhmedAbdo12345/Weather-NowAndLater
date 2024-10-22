package com.example.week_forecast

import com.example.data.models.remote.City
import com.example.data.models.remote.Clouds
import com.example.data.models.remote.Coord
import com.example.data.models.remote.ForecastResponse
import com.example.data.models.remote.ListItem
import com.example.data.models.remote.Rain
import com.example.data.models.remote.Sys
import com.example.data.models.remote.WeatherItem
import com.example.data.models.remote.Wind

fun createMockForecastResponse(): ForecastResponse {
        return ForecastResponse(
            city = City(
                id = 12,
                name = "Cairo",
                country = "EG",
                population = 15,
                sunset = 42,
                sunrise = 21,
                timezone = 1515545,
                coord = Coord(lat = 31.545151, lon = 45.515444)
            ), cnt = 45, cod = "56", message = 56, list = listOf(
                ListItem(
                    dt = 55,
                    pop = 1.3f,
                    visibility = 66,
                    weather = listOf(
                        WeatherItem(
                            id = 12, description = "Clear", icon = "01d", main = null
                        )
                    ),
                    sys = Sys(
                        pod = null, country = "EG", sunset = 1729609917, sunrise = 1729609917
                    ),
                    wind = Wind(deg = 11, gust = 12.5, speed = 8.5),
                    rain = Rain(45.6),
                    dtTxt = "5515",
                    main = null,
                    clouds = Clouds(15)
                )
            )
        )
    }
