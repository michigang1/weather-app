package me.michigang1.weathapp.location
import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager

class LocationRepositoryImpl(private val context: Context) : LocationRepository {

    private val locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private var isLocationListenerStarted = false

    @SuppressLint("MissingPermission")
    override suspend fun currentLocation(callback: (Location) -> Unit) {
        if (!isLocationListenerStarted) {
            isLocationListenerStarted = !isLocationListenerStarted
            val bestProvider = locationManager.getBestProvider(Criteria(), true)
            val locationListener = LocationListener {
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
    companion object {
        private const val REFRESH_RATE = 60_000L
        private const val TRASH_HOLD = 100f
    }
}
