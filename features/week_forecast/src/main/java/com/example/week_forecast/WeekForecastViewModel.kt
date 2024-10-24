package com.example.week_forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.DataHolder
import com.example.data.models.remote.ForecastResponse
import com.example.data.usecase.GetWeekForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeekForecastViewModel @Inject constructor(private val getWeekForecastUseCase: GetWeekForecastUseCase) :
    ViewModel() {

    private val _forecastUiStet = MutableStateFlow<ForecastUiState>(ForecastUiState.Loading)
    val forecastUiState = _forecastUiStet.asStateFlow()
    val intentChannel = Channel<ForecastIntent>(Channel.UNLIMITED)

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is ForecastIntent.FetchWeekForecast -> getWeekForecast(intent.city)
                }
            }
        }
    }

    private fun getWeekForecast(city: String) {
        viewModelScope.launch {

            when (val holder = getWeekForecastUseCase(city)) {
                DataHolder.Loading -> {}
                is DataHolder.Success -> {
                    holder.data?.let {
                        _forecastUiStet.value = ForecastUiState.Success(it)
                    }
                }

                is DataHolder.Fail -> {
                    _forecastUiStet.value = ForecastUiState.Failed(holder.e.message.toString())
                }

            }
        }
    }
}

sealed interface ForecastUiState {
    data object Loading : ForecastUiState
    data class Failed(val message: String) : ForecastUiState
    data class Success(val forecastResponse: ForecastResponse) : ForecastUiState
}

sealed class ForecastIntent {
    data class FetchWeekForecast(val city: String) : ForecastIntent()
}