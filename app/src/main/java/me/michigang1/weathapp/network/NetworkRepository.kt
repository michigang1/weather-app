package me.michigang1.weathapp.network

import android.location.Location
import me.michigang1.weathapp.responses.current.CurrentWeatherResponse
import me.michigang1.weathapp.responses.details.DetailedWeatherResponse

interface NetworkRepository {
    suspend fun fetchCurrentWeather(
        location: Location,
        callback: (Result<CurrentWeatherResponse>) -> Unit
    )
    suspend fun fetchDetailedWeather(
        location: Location,
        callback: (Result<DetailedWeatherResponse>) -> Unit
    )
}
