package me.michigang1.weathapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.michigang1.weathapp.BuildConfig
import me.michigang1.weathapp.R
import me.michigang1.weathapp.responses.details.HourlyWeatherResponse
import me.michigang1.weathapp.utils.Converter

class HourlyWeatherAdapter : RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherHolder>() {

    private val converter = Converter()
    var listOfHourlyWeather = emptyList<HourlyWeatherResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hourly_weather_item, parent, false)
        return HourlyWeatherHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyWeatherHolder, position: Int) {
        with(holder) {
            val temperature = "${converter.kelvinToCelsius(listOfHourlyWeather[position].temp)}°"
            temp.text = temperature
            time.text = converter.timeToHours(listOfHourlyWeather[position].date)
            Glide.with(img)
                .load(
                    "${BuildConfig.IMG_URL}${listOfHourlyWeather[position].weather[0].icon}.png"
                ).into(img)
        }
    }

    override fun getItemCount(): Int = listOfHourlyWeather.size

    inner class HourlyWeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val temp: TextView = itemView.findViewById(R.id.temperature_text_view)
        val time: TextView = itemView.findViewById(R.id.hour_text_view)
        val img: ImageView = itemView.findViewById(R.id.picture_image_view)
    }
}
