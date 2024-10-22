package com.example.current_weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.DataHolder
import com.example.data.models.remote.CurrentResponse
import com.example.data.usecase.GetCityWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityWeatherViewModel  @Inject constructor(private val getCityWeatherUseCase: GetCityWeatherUseCase): ViewModel() {

    private val _homeUiStet = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState = _homeUiStet.asStateFlow()

    fun getCityWeather(city:String){
        viewModelScope.launch {

            when(val holder =getCityWeatherUseCase.execute(city)){
                DataHolder.Loading -> {}
                is DataHolder.Success -> {
                    holder.data?.let {
                        _homeUiStet.value = HomeUiState.Success(it)
                    }
                }
                is DataHolder.Fail -> {
                    _homeUiStet.value = HomeUiState.Failed(holder.e.message.toString())
                }

            }
        }
    }
}

sealed interface HomeUiState{
    data object Loading: HomeUiState
    data class Failed(val message: String): HomeUiState
    data class Success(val currentResponse: CurrentResponse): HomeUiState
}