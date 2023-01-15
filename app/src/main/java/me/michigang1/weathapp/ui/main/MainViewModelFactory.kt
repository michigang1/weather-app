package me.michigang1.weathapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.michigang1.weathapp.location.LocationRepository
import me.michigang1.weathapp.network.NetworkRepository

class MainViewModelFactory(
    private val locationRepository: LocationRepository,
    private val networkRepository: NetworkRepository
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainViewModel(locationRepository, networkRepository) as T
}
