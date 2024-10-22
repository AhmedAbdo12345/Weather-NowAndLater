package com.example.data.usecase

import com.example.data.repository.WeatherRepository
import javax.inject.Inject


class GetWeekForecastUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun execute(city: String) = weatherRepository.getWeekForecast(city)
}