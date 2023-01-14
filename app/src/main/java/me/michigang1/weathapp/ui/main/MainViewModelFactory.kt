package me.michigang1.weathapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.michigang1.weathapp.location.LocationRepository

class MainViewModelFactory(
    private val locationRepository: LocationRepository
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainViewModel(locationRepository) as T
}
