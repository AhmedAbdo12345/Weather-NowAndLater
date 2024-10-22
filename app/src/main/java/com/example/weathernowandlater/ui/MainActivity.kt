package com.example.weathernowandlater.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.util.CURRENT_WEATHER
import com.example.core.util.HOME
import com.example.weathernowandlater.navigation.Nav
import com.example.weathernowandlater.ui.theme.WeatherNowAndLaterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WeatherNowAndLaterTheme {
                val viewModel: CurrentCityViewModel = hiltViewModel()
                val currentCity by viewModel.cityUiState.collectAsState()
                LaunchedEffect(Unit) {
                    viewModel.getCity()
                }

                Scaffold {
                    Nav(
                        modifier = Modifier
                            .consumeWindowInsets(it)
                            .padding(it),
                        startDestination = if (currentCity.isEmpty()) HOME else "$CURRENT_WEATHER/${currentCity}"
                    )
                }
            }
        }
    }
}
