package com.example.data.models.remote

import com.google.gson.annotations.SerializedName

data class Main(
    val humidity: Int?,
    val pressure: Int?,
    val temp: Double?,
    @SerializedName("feels_like") val feelsLike: Double?,
    @SerializedName("grnd_level") val grndLevel: Int?,
    @SerializedName("sea_level") val seaLevel: Int?,
    @SerializedName("temp_kf") val tempKf: Double?,
    @SerializedName("temp_max") val tempMax: Double?,
    @SerializedName("temp_min") val tempMin: Double?
)