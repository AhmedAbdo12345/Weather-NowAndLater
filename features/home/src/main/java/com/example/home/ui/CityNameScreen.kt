package com.example.home.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.util.CURRENT_WEATHER
import com.example.home.R

private const val TAG = "CityNameScreen"
@Composable
fun CityNameScreen(
    viewModel: SaveCityNameViewModel= hiltViewModel(),
    navController: NavHostController
) {
    val saveCityUiState by viewModel.saveCityUiState.collectAsState()

    when (saveCityUiState) {
        true -> {
            Log.d(TAG, "CityNameScreen: true")
        }
        false -> {
            Log.d(TAG, "CityNameScreen: false")
        }
    }
    var city by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = city,
            onValueChange = { query ->
                city = query
            },
            label = { Text(text = stringResource(R.string.enter_city_name)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (city.isNotEmpty()) {
            Button(onClick = {
                viewModel.saveCity(city)
                navController.navigate("$CURRENT_WEATHER/$city")
            }, enabled = true ) {
                Text(
                    text = "Get Weather in $city",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(vertical = 8.dp)
                )
            }

        }
    }
}
