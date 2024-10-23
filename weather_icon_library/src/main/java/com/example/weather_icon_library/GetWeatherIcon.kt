package com.example.weather_icon_library

fun getWeatherIcon(icon: String) = when (icon) {
    "01d" -> R.drawable.ic_sun
    "01n" -> R.drawable.ic_moon

    "02d" -> R.drawable.ic_sun_cloud
    "02n" -> R.drawable.ic_cloud_night

    "03d", "03n" -> R.drawable.ic_cloud

    "04d", "04n" -> R.drawable.ic_broken_cloud

    "09d", "09n" -> R.drawable.ic_rain

    "10d", "10n" -> R.drawable.ic_sun_cloud_and_rain

    "11d", "11n" -> R.drawable.ic_storm

    "13d", "13n" -> R.drawable.ic_storm

    "50d", "50n" -> R.drawable.ic_mist
    else -> R.drawable.ic_placeholder_weather
}
    
