package com.example.data

import com.example.data.datasource.local.LocalDataSourceInterface

class FakeLocalDataSource: LocalDataSourceInterface {

    private var cityName: String? = null

    override suspend fun saveCity(city: String): Boolean {
        cityName = city
        return true
    }

    override suspend fun getCity(): String? {
        return cityName
    }

}