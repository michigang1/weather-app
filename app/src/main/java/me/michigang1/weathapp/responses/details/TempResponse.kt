package me.michigang1.weathapp.responses.details

import com.google.gson.annotations.SerializedName

data class TempResponse(
    @SerializedName("min") val minTemp: Double,
    @SerializedName("max") val maxTemp: Double
)
