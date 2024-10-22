package com.example.weathernowandlater.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.util.CURRENT_WEATHER
import com.example.core.util.HOME
import com.example.core.util.WEEK_FORECAST
import com.example.current_weather.ui.CurrentWeatherScreen
import com.example.home.ui.CityNameScreen
import com.example.week_forecast.WeekForecastScreen


@Composable
fun Nav(modifier: Modifier,startDestination: String = HOME){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier){
        composable(HOME){
            CityNameScreen(navController = navController)
        }


        composable("$CURRENT_WEATHER/{cityName}") { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName").orEmpty()
            CurrentWeatherScreen(navController = navController, cityName = cityName)
        }

        composable("$WEEK_FORECAST/{city}") { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("city").orEmpty()
            WeekForecastScreen(city = cityName)
        }

    }
}