package com.example.weathernowandlater.di

import android.content.Context
import com.example.data.api.WeatherServices
import com.example.data.database.DataStoreManger
import com.example.data.datasource.local.LocalDataSource
import com.example.data.datasource.remote.RemoteDataSource
import com.example.data.repository.WeatherRepository
import com.example.data.repository.WeatherRepositoryImplementation
import com.example.data.usecase.GetCityWeatherUseCase
import com.example.data.usecase.GetWeekForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BASIC
    )

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): WeatherServices {
        return retrofit.create(WeatherServices::class.java)
    }


    @Provides
    fun provideWeatherRepository(
        localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource
    ): WeatherRepository {
        return WeatherRepositoryImplementation(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @Provides
    fun provideGetCityWeatherUseCase(
       weatherRepository: WeatherRepository
    ): GetCityWeatherUseCase {
        return GetCityWeatherUseCase(weatherRepository)
    }

    @Provides
    fun provideGetWeekForecastUseCase(
       weatherRepository: WeatherRepository
    ): GetWeekForecastUseCase {
        return GetWeekForecastUseCase(weatherRepository)
    }

    @Provides
    fun getDataStoreManger(@ApplicationContext  context: Context) = DataStoreManger(context)

    @Provides
    fun getLocalDataSource(dataStoreManger: DataStoreManger) = LocalDataSource(dataStoreManger)


    @Provides
    fun getRemoteDataSource(weatherServices: WeatherServices) = RemoteDataSource(weatherServices)



}