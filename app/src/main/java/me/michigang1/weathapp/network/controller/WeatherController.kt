package me.michigang1.weathapp.network.controller

import me.michigang1.weathapp.entities.CurrentWeatherEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherController {
    @GET("weather")
    fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("appid") apiKey: String
    ): Call<CurrentWeatherEntity>
}
