package com.sworks.weatherforecasting.ui.home.viewmodel


import androidx.lifecycle.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.sworks.weatherforecasting.ui.home.repo.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository,
                                        val mFusedLocationProviderClient : FusedLocationProviderClient) :ViewModel() {

    var isGpsEnabled = MutableLiveData<Boolean>()
    var isNetworkAvailable = MutableLiveData<Boolean>()
    var isPermissionGranted = MutableLiveData<Boolean>()
    var currentCity:String? = null

    fun setCity(city: String) {
        currentCity = city
    }

    fun setGpsStatus(value: Boolean) {
        isGpsEnabled.postValue(value)
    }

    fun setNetworkAvailable(value: Boolean) {
        isNetworkAvailable.postValue(value)
    }

    fun setPermissionGranted(value: Boolean) {
        isPermissionGranted.postValue(value)
    }


    val readyToFetch = MediatorLiveData<Map<String, Boolean>>().apply {
        addSource(isGpsEnabled) { gps ->
            value = isNetworkAvailable.value?.let { network ->
                isPermissionGranted.value?.let { permission ->
                    mapOf(
                        "permission" to permission,
                        "network" to network,
                        "gps" to gps
                    )
                }
            }
        }
        addSource(isNetworkAvailable) { network ->
            value = isGpsEnabled.value?.let { gps ->
                isPermissionGranted.value?.let { permission ->
                    mapOf(
                        "permission" to permission,
                        "network" to network,
                        "gps" to gps
                    )
                }
            }
        }
        addSource(isPermissionGranted) { permission ->
            value = isGpsEnabled.value?.let { gps ->
                isNetworkAvailable.value?.let { network ->
                    mapOf(
                        "permission" to permission,
                        "network" to network,
                        "gps" to gps
                    )
                }
            }
        }
    }

    fun fetchWeatherLatLong() = homeRepository.getWeatherOfLatLon().asLiveData()

    fun fetchWeatherByCity(city:String) = homeRepository.fetchWeatherForLocation(city).asLiveData()

}