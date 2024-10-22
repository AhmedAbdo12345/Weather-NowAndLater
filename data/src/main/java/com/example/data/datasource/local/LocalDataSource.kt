package com.example.data.datasource.local

import com.example.data.database.DataStoreManger
import javax.inject.Inject

class LocalDataSource @Inject constructor(val dataStoreManger: DataStoreManger) :
    LocalDataSourceInterface {

    override suspend fun getCity(): String? {
        return dataStoreManger.getCity()
    }

    override suspend fun saveCity(city: String):Boolean {
      return  dataStoreManger.saveCity(city)
    }
}