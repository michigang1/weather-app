package me.michigang1.weathapp.entities

import com.google.gson.annotations.SerializedName
class MainEntity {
    @SerializedName("feels_like")
    var feelsLike: Double? = null

    @SerializedName("temp")
    var temp: Double? = null

    @SerializedName("temp_max")
    var tempMax: Double? = null

    @SerializedName("temp_min")
    var tempMin: Double? = null
}
