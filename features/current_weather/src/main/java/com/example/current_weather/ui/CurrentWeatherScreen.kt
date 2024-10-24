package com.example.current_weather.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.theme.LiteBlue
import com.example.core.theme.Yellow
import com.example.core.util.CircularProgressBar
import com.example.core.util.HOME
import com.example.core.util.WEEK_FORECAST
import com.example.core.util.convertDtToDateTime
import com.example.core.util.convertKelvinToCelsius
import com.example.current_weather.R
import com.example.current_weather.viewmodel.CityWeatherViewModel
import com.example.current_weather.viewmodel.HomeUiState
import com.example.data.models.remote.CurrentResponse
import com.example.weather_icon_library.getWeatherIcon

private const val TAG = "DisplayCurrentWeatherSc"

@Composable
fun CurrentWeatherScreen(
    viewModel: CityWeatherViewModel = hiltViewModel(),
    navController: NavHostController,
    cityName: String
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getCityWeather(cityName)
    }
    when (homeUiState) {
        is HomeUiState.Loading -> {
            CircularProgressBar()
        }

        is HomeUiState.Success -> {
            val currentResponse = (homeUiState as? HomeUiState.Success)?.currentResponse
            Log.d(TAG, "HomeScreen: $currentResponse")
            currentResponse?.let {
                DisplayData(currentResponse = it, navController = navController, cityName)
            }
        }

        is HomeUiState.Failed -> {
            val errorMessage = (homeUiState as? HomeUiState.Failed)?.message
            Log.d(TAG, "HomeScreenError: $errorMessage")
            Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()

        }
    }


}

@Composable
fun DisplayData(
    currentResponse: CurrentResponse,
    navController: NavHostController,
    cityName: String
) {
    Column {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = currentResponse.name.orEmpty(), fontSize = 24.sp)

            Spacer(modifier = Modifier.height(8.dp))
            currentResponse.dt?.let {
             Text(text = convertDtToDateTime(it), fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Icon(
                painter = painterResource(id = getWeatherIcon(currentResponse.weather?.firstOrNull()?.icon.orEmpty())),
                contentDescription = currentResponse.weather?.firstOrNull()?.description,
                modifier = Modifier.size(60.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = currentResponse.weather?.firstOrNull()?.description.orEmpty(),
                fontSize = 18.sp
            )

        }
        WeatherDashboard(currentResponse)
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { navController.navigate(HOME) }) {
                Text(text = "Change City", fontSize = 14.sp)
            }
            Button(onClick = { navController.navigate("$WEEK_FORECAST/$cityName") }) {
                Text(text = "Show Weekly Forecast", fontSize = 14.sp)
            }
        }
    }


}


@Composable
fun WeatherInfoCard(name: String, value: String, iconId: Int) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(LiteBlue, RoundedCornerShape(8.dp))
            .padding(4.dp)
            .width(100.dp)
            .height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name, style = MaterialTheme.typography.titleSmall, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = name,
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Yellow
            ),
            textAlign = TextAlign.Center
        )


    }
}

@Composable
fun WeatherDashboard(currentResponse: CurrentResponse) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            WeatherInfoCard(
                name = stringResource(R.string.temperature),
                value = convertKelvinToCelsius(currentResponse.main?.temp),
                iconId = com.example.core.R.drawable.ic_temperature
            )
            WeatherInfoCard(
                name = stringResource(R.string.temp_min),
                value = convertKelvinToCelsius(currentResponse.main?.tempMin),
                iconId = com.example.core.R.drawable.ic_cold_temperature
            )
            WeatherInfoCard(
                name = stringResource(R.string.temp_max),
                value = convertKelvinToCelsius(currentResponse.main?.tempMax),
                iconId = com.example.core.R.drawable.ic_hot_temperature
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            WeatherInfoCard(
                name = stringResource(R.string.sea_level),
                value = currentResponse.main?.seaLevel.toString(),
                iconId = com.example.core.R.drawable.ic_sea_level
            )
            WeatherInfoCard(
                name = stringResource(R.string.humidity),
                value = currentResponse.main?.humidity.toString(),
                iconId = com.example.core.R.drawable.ic_humidity
            )
            WeatherInfoCard(
                name = stringResource(R.string.pressure),
                value = currentResponse.main?.pressure.toString(),
                iconId = com.example.core.R.drawable.ic_pressure
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            WeatherInfoCard(
                name = stringResource(R.string.wind_speed),
                value = currentResponse.wind?.speed.toString(),
                iconId = com.example.core.R.drawable.ic_wind
            )

        }
    }
}
