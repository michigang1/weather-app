package me.michigang1.weathapp.responses.details

import com.google.gson.annotations.SerializedName
import me.michigang1.weathapp.responses.WeatherResponse

data class HourlyWeatherResponse(
    @SerializedName("dt") val date: Long,
    @SerializedName("temp") val temp: Double,
    @SerializedName("weather") val weather: List<WeatherResponse>
)
