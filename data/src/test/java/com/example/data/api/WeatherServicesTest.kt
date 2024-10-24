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
import retrofit2.Call
import retrofit2.Response

class WeatherServicesTest {
    private lateinit var mockWeatherServices: WeatherServices
    private lateinit var mockCityWeatherCall: Call<CurrentResponse>
    private lateinit var mockWeekForecastCall: Call<ForecastResponse>
    @Before
    fun setUp() {
        mockWeatherServices = mock()
        mockCityWeatherCall = mock()
        mockWeekForecastCall = mock()
    }

    private fun createMockErrorResponse(): Response<CurrentResponse> {
        val errorBody = """{"error":"City not found"}"""
            .toResponseBody("application/json".toMediaTypeOrNull())
        return Response.error(404, errorBody)
    }

    @Test
    fun testGetCityWeatherSuccessfulResponse() = runBlocking {
        // Given
        val city = "Cairo"
        val mockResponse = Response.success(createMockCityWeatherResponse())

        `when`(mockWeatherServices.getCityWeather(city)).thenReturn(mockCityWeatherCall)
        `when`(mockCityWeatherCall.execute()).thenReturn(mockResponse)

        // When
        val response = mockWeatherServices.getCityWeather(city).execute()

        // Then
        assertTrue(response.isSuccessful)
        assertEquals("Cairo", response.body()?.name)
    }

    @Test
    fun testGetWeekForecastSuccessfulResponse() = runBlocking {
        // Given
        val city = "Cairo"
        val mockResponse = Response.success(createMockForecastResponse())
        `when`(mockWeatherServices.getWeekForecast(city = city)).thenReturn(mockWeekForecastCall)
        `when`(mockWeekForecastCall.execute()).thenReturn(mockResponse)

        // When
        val response = mockWeatherServices.getWeekForecast(city = city).execute()

        // Then
        assertTrue(response.isSuccessful)
        assertEquals(city, response.body()?.city?.name)
    }

    @Test
    fun testGetCityWeatherFailedResponse() = runBlocking {
        // Given
        val city = "UnknownCity"
        val mockErrorResponse = createMockErrorResponse()
        `when`(mockCityWeatherCall.execute()).thenReturn(
            mockErrorResponse
        )
        `when`(mockWeatherServices.getCityWeather(city)).thenReturn(mockCityWeatherCall)


        // When
        val response = mockWeatherServices.getCityWeather(city = city).execute()

        // Then
        assertFalse(response.isSuccessful)
        assertEquals(404, response.code())
    }
}
