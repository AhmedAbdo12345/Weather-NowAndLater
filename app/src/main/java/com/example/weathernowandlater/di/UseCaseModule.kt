package com.example.weathernowandlater.di

import com.example.data.repository.WeatherRepository
import com.example.data.usecase.GetCityWeatherUseCase
import com.example.data.usecase.GetWeekForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {


    @Provides
    fun provideGetCityWeatherUseCase(
       weatherRepository: WeatherRepository
    ): GetCityWeatherUseCase {
        return GetCityWeatherUseCase(weatherRepository)
    }

    @Provides
    fun provideGetWeekForecastUseCase(
       weatherRepository: WeatherRepository
    ): GetWeekForecastUseCase {
        return GetWeekForecastUseCase(weatherRepository)
    }


}