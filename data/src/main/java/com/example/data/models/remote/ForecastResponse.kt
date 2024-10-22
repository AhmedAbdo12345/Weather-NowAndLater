package com.example.data.models.remote

data class ForecastResponse(
    val city: City?,
    val cnt: Int?,
    val cod: String?,
    val list: List<ListItem>?,
    val message: Int?
)

