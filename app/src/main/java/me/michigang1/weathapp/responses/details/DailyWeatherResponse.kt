package me.michigang1.weathapp.responses.details

import com.google.gson.annotations.SerializedName
import me.michigang1.weathapp.responses.WeatherResponse

data class DailyWeatherResponse(
    @SerializedName("dt") val date: Long,
    @SerializedName("temp") val temp: TempResponse,
    @SerializedName("weather") val weather: List<WeatherResponse>
)
