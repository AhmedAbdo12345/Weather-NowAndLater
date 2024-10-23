package com.example.data.api

import com.example.data.createMockCityWeatherResponse
import com.example.data.createMockForecastResponse
import com.example.data.models.remote.*
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock
import retrofit2.Response

class WeatherServicesTest {
    private lateinit var mockWeatherServices: WeatherServices

    @Before
    fun setUp() {
        mockWeatherServices = mock()
    }

    private fun createMockErrorResponse(): Response<CurrentResponse> {
        val errorBody = """{"error":"City not found"}"""
            .toResponseBody("application/json".toMediaTypeOrNull())
        return Response.error(404, errorBody)
    }

    @Test
    fun testGetCityWeatherSuccessfulResponse() = runBlocking {
        // Given
        val mockResponse = Response.success(createMockCityWeatherResponse())
        `when`(mockWeatherServices.getCityWeather(city = "Cairo")).thenReturn(mockResponse)

        // When
        val response = mockWeatherServices.getCityWeather(city = "Cairo")

        // Then
        assertTrue(response.isSuccessful)
        assertEquals("Cairo", response.body()?.name)
    }

    @Test
    fun testGetWeekForecastSuccessfulResponse() = runBlocking {
        // Given
        val mockResponse = Response.success(createMockForecastResponse())
        `when`(mockWeatherServices.getWeekForecast(city = "Cairo")).thenReturn(mockResponse)

        // When
        val response = mockWeatherServices.getWeekForecast(city = "Cairo")

        // Then
        assertTrue(response.isSuccessful)
        assertEquals("Cairo", response.body()?.city?.name)
    }

    @Test
    fun testGetCityWeatherFailedResponse() = runBlocking {
        // Given
        val mockErrorResponse = createMockErrorResponse()
        `when`(mockWeatherServices.getCityWeather(city = "UnknownCity")).thenReturn(
            mockErrorResponse
        )

        // When
        val response = mockWeatherServices.getCityWeather(city = "UnknownCity")

        // Then
        assertFalse(response.isSuccessful)
        assertEquals(404, response.code())
    }
}
