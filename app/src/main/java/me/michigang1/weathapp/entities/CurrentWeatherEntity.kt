package me.michigang1.weathapp.entities

import com.google.gson.annotations.SerializedName

class CurrentWeatherEntity {
    @SerializedName("weather")
    var weather: List<WeatherEntity>? = null

    @SerializedName("main")
    var main: MainEntity? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("date")
    var date: Long? = null
}
