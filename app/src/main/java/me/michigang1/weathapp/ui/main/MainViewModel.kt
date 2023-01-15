package me.michigang1.weathapp.ui.main

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.michigang1.weathapp.location.LocationRepository
import me.michigang1.weathapp.network.NetworkRepository
import me.michigang1.weathapp.responses.current.CurrentWeatherResponse
import me.michigang1.weathapp.responses.details.DetailedWeatherResponse

class MainViewModel(
    private val locationRepo: LocationRepository,
    private val networkRepo: NetworkRepository
) : ViewModel() {

    private val _locationLiveData = MutableLiveData<Location>()
    val locationLiveData: LiveData<Location> = _locationLiveData

    private val _currentWeatherLiveData = MutableLiveData<CurrentWeatherResponse>()
    val currentWeatherLiveData: LiveData<CurrentWeatherResponse> = _currentWeatherLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> = _errorLiveData

    private val _progressBarLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData: LiveData<Boolean> = _progressBarLiveData

    private val _detailedWeatherLiveData = MutableLiveData<DetailedWeatherResponse>()
    val detailedWeatherLiveData: LiveData<DetailedWeatherResponse> = _detailedWeatherLiveData

    fun getLocation() {
        viewModelScope.launch {
            locationRepo.currentLocation() {
                _locationLiveData.value = it
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

    fun getDetailedWeather(location: Location) {
        viewModelScope.launch {
            networkRepo.fetchDetailedWeather(location) {
                if (it.isSuccess) {
                    _detailedWeatherLiveData.value = it.getOrNull()
                } else {
                    _errorLiveData.value = it.exceptionOrNull()
                }
            }
        }
    }

    fun getLocationFromAddressName(name: String) {
        if (name.isNotBlank()) {
            _progressBarLiveData.value = true
            viewModelScope.launch {
                val address = locationRepo.getLocationFromAddressName(name)
                if (address != null) {
                    val location = Location("")
                    location.latitude = address.latitude
                    location.longitude = address.longitude
                    _locationLiveData.value = location
                } else {
                    _errorLiveData.value = Throwable("can't find this address")
                }
            }
        }
    }
}
