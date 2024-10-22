package com.example.data.repository

import com.example.core.util.DataHolder
import com.example.data.FakeLocalDataSource
import com.example.data.FakeRemoteDataSource
import com.example.data.createMockCityWeatherResponse
import com.example.data.createMockForecastResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WeatherRepositoryTest {

    private lateinit var weatherRepository: WeatherRepositoryImplementation
    private lateinit var fakeLocalDataSource: FakeLocalDataSource
    private lateinit var fakeRemoteDataSource: FakeRemoteDataSource

    @Before
    fun setUp() {
        fakeLocalDataSource = FakeLocalDataSource()
        fakeRemoteDataSource = FakeRemoteDataSource()
        weatherRepository = WeatherRepositoryImplementation(
            localDataSource = fakeLocalDataSource,
            remoteDataSource = fakeRemoteDataSource
        )
    }


    @Test
    fun getCityWeather() = runBlocking {
        // Given
        val city = "Cairo"
        val mockResponse = createMockCityWeatherResponse()
        fakeRemoteDataSource.setCityWeatherResponse(mockResponse)

        // When
        val result = weatherRepository.getCityWeather(city)

        // Then
        assertEquals(DataHolder.Success(mockResponse), result)
    }

    @Test
    fun getWeekForecast() = runBlocking {
        // Given
        val city = "Cairo"
        val mockResponse = createMockForecastResponse()
        fakeRemoteDataSource.setWeekForecastResponse(mockResponse)

        // When
        val result = weatherRepository.getWeekForecast(city)

        // Then
        assertEquals(DataHolder.Success(mockResponse), result)
    }

    @Test
    fun saveCity() = runBlocking {
        // Given
        val city = "Cairo"

        // When
        val result = weatherRepository.saveCity(city)

        // Then
        assertTrue(result)
        assertEquals(city, fakeLocalDataSource.getCity())
    }

    @Test
    fun getCity() = runBlocking {
        // given
        val expectedCity = "Cairo"
        fakeLocalDataSource.saveCity(expectedCity)

        // When
        val result = weatherRepository.getCity()

        // Then
        assertEquals(expectedCity, result)
    }

}
