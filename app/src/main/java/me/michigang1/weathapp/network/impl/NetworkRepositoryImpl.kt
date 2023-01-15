package me.michigang1.weathapp.network.impl

import android.location.Location
import me.michigang1.weathapp.BuildConfig
import me.michigang1.weathapp.BuildConfig.WEATHER_API_KEY
import me.michigang1.weathapp.entities.CurrentWeatherEntity
import me.michigang1.weathapp.network.NetworkRepository
import me.michigang1.weathapp.network.controller.WeatherController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepositoryImpl(
    private val weatherApi: WeatherController
) : NetworkRepository {
    override suspend fun fetchCurrentWeather(
        location: Location,
        callback: (Result<CurrentWeatherEntity>) -> Unit
    ) {
        weatherApi.getCurrentWeather(
            location.latitude,
            location.longitude,
            WEATHER_API_KEY
        ).enqueue(object : Callback<CurrentWeatherEntity> {
            override fun onResponse(
                call: Call<CurrentWeatherEntity>,
                response: Response<CurrentWeatherEntity>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    callback.invoke(Result.success(response.body()!!))
                } else {
                    callback.invoke(Result.failure(Throwable(response.code().toString())))
                }
            }

            override fun onFailure(call: Call<CurrentWeatherEntity>, t: Throwable) {
                callback.invoke(Result.failure(Throwable(t)))
            }
        })
    }
}
