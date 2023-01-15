package me.michigang1.weathapp.network.impl

import android.location.Location
import me.michigang1.weathapp.BuildConfig
import me.michigang1.weathapp.BuildConfig.WEATHER_API_KEY
import me.michigang1.weathapp.network.NetworkRepository
import me.michigang1.weathapp.network.controller.WeatherController
import me.michigang1.weathapp.responses.current.CurrentWeatherResponse
import me.michigang1.weathapp.responses.details.DetailedWeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepositoryImpl(
    private val weatherApi: WeatherController
) : NetworkRepository {
    override suspend fun fetchCurrentWeather(
        location: Location,
        callback: (Result<CurrentWeatherResponse>) -> Unit
    ) {
        weatherApi.getCurrentWeather(
            location.latitude,
            location.longitude,
            WEATHER_API_KEY
        ).enqueue(object : Callback<CurrentWeatherResponse> {
            override fun onResponse(
                call: Call<CurrentWeatherResponse>,
                response: Response<CurrentWeatherResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    callback.invoke(Result.success(response.body()!!))
                } else {
                    callback.invoke(Result.failure(Throwable(response.code().toString())))
                }
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                callback.invoke(Result.failure(Throwable(t)))
            }
        })
    }

    override suspend fun fetchDetailedWeather(
        location: Location,
        callback: (Result<DetailedWeatherResponse>) -> Unit
    ) {
        weatherApi.getDetailedWeatherData(
            location.latitude,
            location.longitude,
            BuildConfig.WEATHER_API_KEY
        ).enqueue(object : Callback<DetailedWeatherResponse> {
            override fun onResponse(
                call: Call<DetailedWeatherResponse>,
                response: Response<DetailedWeatherResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    callback.invoke(Result.success(response.body()!!))
                } else {
                    callback.invoke(Result.failure(Throwable(response.code().toString())))
                }
            }

            override fun onFailure(call: Call<DetailedWeatherResponse>, t: Throwable) {
                callback.invoke(Result.failure(Throwable(t)))
            }
        })
    }
}
