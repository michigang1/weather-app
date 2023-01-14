package me.michigang1.weathapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.michigang1.weathapp.location.LocationRepository

class MainViewModel(private val locationRepo: LocationRepository) : ViewModel() {

    private val _lastLocation = MutableLiveData<String>()
    val lastLocation: LiveData<String> = _lastLocation

    fun getLocation() {
        viewModelScope.launch {
            locationRepo.currentLocation() {
                _lastLocation.value = "${it.latitude} ${it.longitude}"
            }
        }
    }
}
