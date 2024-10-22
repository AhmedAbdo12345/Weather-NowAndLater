package com.example.data.models.remote

data class WeatherItem(
    val id: Int,
    val description: String?,
    val icon: String?,
    val main: String?
)