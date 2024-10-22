package com.example.week_forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.util.changeFormatTime
import com.example.data.models.remote.ForecastResponse
import com.example.data.models.remote.ListItem

@Composable
fun WeekForecastScreen(viewModel: WeekForecastViewModel = hiltViewModel(),city: String){

    val uiState by viewModel.forecastUiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.intentChannel.send(ForecastIntent.FetchWeekForecast(city))
    }

    when (uiState) {
        is ForecastUiState.Loading -> {
        }
        is ForecastUiState.Success -> {
            val forecast = (uiState as ForecastUiState.Success).forecastResponse
            DisplayForecast(forecast)
        }
        is ForecastUiState.Failed -> {
            val errorMessage = (uiState as ForecastUiState.Failed).message
        }
    }
}

@Composable
fun DisplayForecast(forecastResponse: ForecastResponse){
    val itemList = forecastResponse.list ?: emptyList()

    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )  {
            items(itemList) { item ->
                WeatherListItem(item)
            }

    }
}
@Composable
fun WeatherListItem(item: ListItem) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFFE1F5FE), RoundedCornerShape(8.dp))
            .padding(4.dp)
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        item.dtTxt?.let {
            Text(text = "${changeFormatTime(it).first}   ${changeFormatTime(it).second}"  )
        }

        Text(text =item.main?.temp.toString(), fontSize = 14.sp)

        Icon(
            painter = painterResource(id = com.example.weather_icon_library.getWeatherIcon(item.weather?.firstOrNull()?.icon.orEmpty())),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = Color.Unspecified
        )
    }
}