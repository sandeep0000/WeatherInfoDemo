package com.sworks.weatherforecasting.ui.home.repo

import com.sworks.weatherforecasting.data.ResultData
import com.sworks.weatherforecasting.domain.model.WeatherCityResponse
import com.sworks.weatherforecasting.ui.home.data.LocationProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect

class HomeRepository(
    private val homeRepositoryImp: HomeRepositoryImp,
    private val useCase: LocationProvider,
) {
    // Retrofit Api
    fun getWeatherOfLatLon(): Flow<ResultData<WeatherCityResponse>> = flow {
        useCase.fetchLocation().collect { deviceLocation ->
            emit(homeRepositoryImp.getWeatherOfLatLon(deviceLocation.latitude.toString(),
                deviceLocation.longitude.toString()))
        }
    }

    fun fetchWeatherForLocation(city: String): Flow<ResultData<WeatherCityResponse>> = flow {
        emit(homeRepositoryImp.fetchWeatherForLocation(city))
    }
}