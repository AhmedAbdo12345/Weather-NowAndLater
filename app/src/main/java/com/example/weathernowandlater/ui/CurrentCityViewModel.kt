package com.example.weathernowandlater.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentCityViewModel @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {

    private val _cityUiState = MutableStateFlow("")
    val cityUiState = _cityUiState.asStateFlow()


    fun getCity(){
        viewModelScope.launch {
            _cityUiState.value =  weatherRepository.getCity().orEmpty()
        }
    }

}
