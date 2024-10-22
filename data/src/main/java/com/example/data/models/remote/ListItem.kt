package com.example.data.models.remote

import com.google.gson.annotations.SerializedName

data class ListItem(
    val dt: Int?,
    val pop: Float?,
    val visibility: Int?,
    val weather: List<WeatherItem?>?,
    val main: Main?,
    val clouds: Clouds?,
    val sys: Sys?,
    val wind: Wind?,
    val rain: Rain?,
    @SerializedName("dt_txt") val dtTxt: String?,
)