package me.michigang1.weathapp.location
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager

class LocationRepositoryImpl(context: Context) : LocationRepository {

    private val locationService = context.getSystemService(Context.LOCATION_SERVICE)
    private val locationManager: LocationManager = locationService as LocationManager
    private lateinit var locationListener: LocationListener

    private var isLocationListenerStarted = false

    @SuppressLint("MissingPermission")
    override suspend fun currentLocation(callback: (Location) -> Unit) {
        if (!isLocationListenerStarted) {
            isLocationListenerStarted = !isLocationListenerStarted
            locationListener = LocationListener {
                callback.invoke(it)
            }

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                REFRESH_RATE,
                500f,
                locationListener
            )

            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                REFRESH_RATE,
                TRASH_HOLD,
                locationListener
            )
        }
    }

    override suspend fun removeUpdates() {
        locationManager.removeUpdates(locationListener)
    }

    companion object {
        private const val REFRESH_RATE = 60_000L
        private const val TRASH_HOLD = 100f
    }
}
