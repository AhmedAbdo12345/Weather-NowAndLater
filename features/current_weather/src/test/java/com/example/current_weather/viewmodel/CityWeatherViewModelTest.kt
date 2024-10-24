package com.example.current_weather.viewmodel

import app.cash.turbine.test
import com.example.core.util.DataHolder
import com.example.current_weather.createMockCityWeatherResponse
import com.example.data.models.remote.CurrentResponse
import com.example.data.usecase.GetCityWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CityWeatherViewModelTest {

    private lateinit var viewModel: CityWeatherViewModel
    @Mock
    private lateinit var getCityWeatherUseCase: GetCityWeatherUseCase

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        getCityWeatherUseCase = mock()
        Dispatchers.setMain(dispatcher)
        viewModel = CityWeatherViewModel(getCityWeatherUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCityWeather should emit Success when use case returns success`() = runTest {
        // Given
        val mockCurrentResponse = createMockCityWeatherResponse()
        val mockResponse: DataHolder<CurrentResponse?> = DataHolder.Success(mockCurrentResponse)

        `when`(getCityWeatherUseCase(city = "Cairo")).thenReturn(mockResponse)

        // When
        viewModel.getCityWeather("Cairo")

        //Then
        viewModel.homeUiState.test {
            assertEquals(HomeUiState.Loading, awaitItem())
            val actualState = awaitItem()
            assertTrue(actualState is HomeUiState.Success)

            assertEquals(mockCurrentResponse, (actualState as HomeUiState.Success).currentResponse)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `getCityWeather should emit Failed when use case returns failure`() = runTest {
        // Given
        val errorMessage = "City not found"
        whenever(getCityWeatherUseCase("Cairo")).thenReturn(DataHolder.Fail(Exception(errorMessage)))

        // When
        viewModel.getCityWeather("Cairo")

        // Then
        viewModel.homeUiState.test {
            assertEquals(HomeUiState.Loading, awaitItem())
            assertEquals(HomeUiState.Failed(errorMessage), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getCityWeather should remain in Loading state when use case returns Loading`() = runTest {
        // Given
        whenever(getCityWeatherUseCase("Cairo")).thenReturn(DataHolder.Loading)

        // When
        viewModel.getCityWeather("Cairo")

        // Then
        viewModel.homeUiState.test {
            assertEquals(HomeUiState.Loading, awaitItem())
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }
}
