package me.michigang1.weathapp.ui.main

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.michigang1.weathapp.entities.CurrentWeatherEntity
import me.michigang1.weathapp.location.LocationRepository
import me.michigang1.weathapp.network.NetworkRepository

class MainViewModel(
    private val locationRepo: LocationRepository,
    private val networkRepo: NetworkRepository
) : ViewModel() {

    private val _lastLocationLiveData = MutableLiveData<Location>()
    val lastLocationLiveData: LiveData<Location> = _lastLocationLiveData

    private val _currentWeatherLiveData = MutableLiveData<CurrentWeatherEntity>()
    val currentWeatherLiveData: LiveData<CurrentWeatherEntity> = _currentWeatherLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> = _errorLiveData

    private val _progressBarLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData: LiveData<Boolean> = _progressBarLiveData

    fun getLocation() {
        viewModelScope.launch {
            locationRepo.currentLocation() {
                _lastLocationLiveData.value = it
            }
            locationRepo.removeUpdates()
        }
    }

    fun getCurrentWeather(location: Location) {
        viewModelScope.launch {
            networkRepo.fetchCurrentWeather(location) {
                if (it.isSuccess) {
                    _currentWeatherLiveData.value = it.getOrNull()
                } else {
                    _errorLiveData.value = it.exceptionOrNull()
                }
            }
        }
        _progressBarLiveData.value = false
    }
}
