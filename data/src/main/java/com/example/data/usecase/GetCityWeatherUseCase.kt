package com.example.data.usecase

import com.example.data.repository.WeatherRepository
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor( val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city:String) = weatherRepository.getCityWeather(city)
}