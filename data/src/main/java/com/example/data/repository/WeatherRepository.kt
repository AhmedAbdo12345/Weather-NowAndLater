package com.example.data.repository

import com.example.data.datasource.local.LocalDataSourceInterface
import com.example.data.datasource.remote.RemoteDataSourceInterface

interface WeatherRepository : LocalDataSourceInterface, RemoteDataSourceInterface