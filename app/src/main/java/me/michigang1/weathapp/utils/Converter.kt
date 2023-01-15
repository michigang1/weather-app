package me.michigang1.weathapp.utils

import java.text.SimpleDateFormat
import java.util.*

class Converter {
    fun kelvinToCelsius(kelvins: Double): Int =
        (kelvins - KELVIN_NULL).toInt()

    fun timeToHours(timesTamp: Long): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.US)
        val date = Date(timesTamp * 1000)
        return sdf.format(date)
    }

    fun timeToDate(timesTamp: Long): String {
        val sdf = SimpleDateFormat("EEE',' MMM d", Locale.US)
        val date = Date(timesTamp * 1000)
        return sdf.format(date)
    }

    companion object {
        private const val KELVIN_NULL = 273.15
    }
}
