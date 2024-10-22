package com.example.data.datasource.local

interface LocalDataSourceInterface {
    suspend fun saveCity(city: String): Boolean
    suspend fun getCity(): String?
}