package com.example.data.models.remote


class CurrentResponse(
    val id: Int,
    val name: String?,
    val visibility: Int?,
    val timezone: Int?,
    val main: Main?,
    val clouds: Clouds?,
    val sys: Sys?,
    val dt: Int?,
    val coord: Coord?,
    val weather: List<WeatherItem>?,
    val cod: Int?,
    val base: String?,
    val wind: Wind?
)

