package com.example.data.models.remote

data class City(
    val id: Int,
    val coord: Coord?,
    val country: String?,
    val name: String?,
    val population: Int?,
    val sunrise: Int?,
    val sunset: Int?,
    val timezone: Int?
)