package me.michigang1.weathapp.responses.current

import com.google.gson.annotations.SerializedName
import me.michigang1.weathapp.responses.WeatherResponse

class CurrentWeatherResponse {
    @SerializedName("weather")
    var weather: List<WeatherResponse>? = null

    @SerializedName("main")
    var main: MainResponse? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("dt")
    var date: Long? = null
}
