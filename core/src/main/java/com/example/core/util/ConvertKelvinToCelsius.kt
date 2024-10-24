package com.example.core.util

import java.math.RoundingMode
import java.text.DecimalFormat

fun convertKelvinToCelsius(temp: Double?): String {
    val celsius = temp?.minus(273.15)

    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return "${df.format(celsius)}°C"

}
