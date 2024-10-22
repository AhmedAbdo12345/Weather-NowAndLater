package com.example.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveCityNameViewModel @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {

    private val _saveCityUiState = MutableStateFlow(false)
    val saveCityUiState = _saveCityUiState.asStateFlow()

    fun saveCity(city: String){
        viewModelScope.launch {
            _saveCityUiState.value = weatherRepository.saveCity(city)
        }
    }
}
