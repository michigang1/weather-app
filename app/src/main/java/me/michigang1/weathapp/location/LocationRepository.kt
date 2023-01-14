package me.michigang1.weathapp.location

import android.location.Location

interface LocationRepository {
    suspend fun currentLocation(callback: (Location) -> Unit)

    suspend fun removeUpdates()
}
