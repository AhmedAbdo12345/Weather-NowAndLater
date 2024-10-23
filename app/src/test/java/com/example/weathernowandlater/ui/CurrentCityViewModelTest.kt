package com.example.weathernowandlater.ui

import app.cash.turbine.test
import com.example.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class CurrentCityViewModelTest {

    private lateinit var mockWeatherRepository: WeatherRepository
    private val dispatcher = StandardTestDispatcher()
    private lateinit var viewModel: CurrentCityViewModel

    @Before
    fun setUp() {
        mockWeatherRepository = mock()
        viewModel = CurrentCityViewModel(mockWeatherRepository)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
       Dispatchers.resetMain()
    }

    @Test
    fun `getCity should emit correct city name`() = runTest {
        // Given
        `when`(mockWeatherRepository.getCity()).thenReturn("Cairo")

        // When
        viewModel.getCity()

        // Then
        viewModel.cityUiState.test {
            assertEquals("", awaitItem())
            assertEquals("Cairo", awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
