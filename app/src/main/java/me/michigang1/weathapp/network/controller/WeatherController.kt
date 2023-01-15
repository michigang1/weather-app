package me.michigang1.weathapp.network.controller

import me.michigang1.weathapp.responses.current.CurrentWeatherResponse
import me.michigang1.weathapp.responses.details.DetailedWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherController {
    @GET("weather")
    fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("appid") apiKey: String
    ): Call<CurrentWeatherResponse>

    @GET("onecall")
    fun getDetailedWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("appid") apiKey: String,
        @Query("exclude") exclude: String = "current,minutely"
    ): Call<DetailedWeatherResponse>
}
