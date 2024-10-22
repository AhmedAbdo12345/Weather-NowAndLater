package com.example.week_forecast

import app.cash.turbine.test
import com.example.core.util.DataHolder
import com.example.data.models.remote.ForecastResponse
import com.example.data.usecase.GetWeekForecastUseCase
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
class WeekForecastViewModelTest {

    private lateinit var viewModel: WeekForecastViewModel
    @Mock
    private lateinit var getWeekForecastUseCase: GetWeekForecastUseCase

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        getWeekForecastUseCase = mock()
        Dispatchers.setMain(dispatcher)
        viewModel = WeekForecastViewModel(getWeekForecastUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCityWeather should emit Success when use case returns success`() = runTest {
        // Given
        val mockWeekForecastResponse = createMockForecastResponse()
        val mockResponse: DataHolder<ForecastResponse?> = DataHolder.Success(mockWeekForecastResponse)

        `when`(getWeekForecastUseCase.execute(city = "Cairo")).thenReturn(mockResponse)

        // When
        viewModel.intentChannel.send(ForecastIntent.FetchWeekForecast("Cairo"))

        //Then
        viewModel.forecastUiState.test {
            assertEquals(ForecastUiState.Loading, awaitItem())
            val actualState = awaitItem()
            assertTrue(actualState is ForecastUiState.Success)

            assertEquals(mockWeekForecastResponse, (actualState as ForecastUiState.Success).forecastResponse)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `getCityWeather should emit Failed when use case returns failure`() = runTest {
        // Given
        val errorMessage = "City not found"
        whenever(getWeekForecastUseCase.execute("Cairo")).thenReturn(DataHolder.Fail(Exception(errorMessage)))

        // When
        viewModel.intentChannel.send(ForecastIntent.FetchWeekForecast("Cairo"))

        // Then
        viewModel.forecastUiState.test {
            assertEquals(ForecastUiState.Loading, awaitItem())
            assertEquals(ForecastUiState.Failed(errorMessage), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getCityWeather should remain in Loading state when use case returns Loading`() = runTest {
        // Given
        whenever(getWeekForecastUseCase.execute("Cairo")).thenReturn(DataHolder.Loading)

        // When
        viewModel.intentChannel.send(ForecastIntent.FetchWeekForecast("Cairo"))

        // Then
        viewModel.forecastUiState.test {
            assertEquals(ForecastUiState.Loading, awaitItem())
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }
}
