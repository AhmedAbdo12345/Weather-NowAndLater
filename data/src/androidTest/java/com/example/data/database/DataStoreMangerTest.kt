package com.example.data.database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DataStoreManagerTest {

    private lateinit var dataStoreManger: DataStoreManger
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        dataStoreManger = DataStoreManger(context)
    }

    @Test
    fun testSaveCity() = runBlocking {
        // Given
        val city = "Cairo"
        // When
        val result = dataStoreManger.saveCity(city)

        //Then
        assertTrue(result)
        val savedCity = context.dataStore.data.first()[CITY_NAME_KEY]
        assertEquals("Cairo", savedCity)
    }

    @Test
    fun testGetCity() = runBlocking {
        // Given
        dataStoreManger.saveCity("Cairo")

        // When
        val city = dataStoreManger.getCity()

        // Then
        assertEquals("Cairo", city)
    }

}
