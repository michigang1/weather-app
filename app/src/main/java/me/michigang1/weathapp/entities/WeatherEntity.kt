package me.michigang1.weathapp.entities

import com.google.gson.annotations.SerializedName

class WeatherEntity {
    @SerializedName("description")
    var description: String? = null

    @SerializedName("icon")
    var icon: String? = null
}
