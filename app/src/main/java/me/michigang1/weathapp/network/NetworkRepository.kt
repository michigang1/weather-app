package me.michigang1.weathapp.network

import android.location.Location
import me.michigang1.weathapp.entities.CurrentWeatherEntity

interface NetworkRepository {
    suspend fun fetchCurrentWeather(
        location: Location,
        callback: (Result<CurrentWeatherEntity>) -> Unit
    )
}
