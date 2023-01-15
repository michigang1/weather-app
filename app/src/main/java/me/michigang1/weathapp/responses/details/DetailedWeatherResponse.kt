package me.michigang1.weathapp.responses.details

import com.google.gson.annotations.SerializedName

data class DetailedWeatherResponse(
    @SerializedName("hourly") val hourlyWeather: List<HourlyWeatherResponse>,
    @SerializedName("daily") val dailyWeather: List<DailyWeatherResponse>
)
