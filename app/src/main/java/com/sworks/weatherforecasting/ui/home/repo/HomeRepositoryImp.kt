package com.sworks.weatherforecasting.ui.home.repo

import com.sworks.weatherforecasting.data.ResultData
import com.sworks.weatherforecasting.domain.api.ApiService
import com.sworks.weatherforecasting.domain.api.BaseDataSource

class HomeRepositoryImp(
    private val apiService: ApiService
) : BaseDataSource() {

    // Retrofit Api
    suspend fun getWeatherOfLatLon(latitude:String, longitude:String) = getResult {
        apiService.getWeatherOfLatLon(latitude, longitude)
    }

    suspend fun fetchWeatherForLocation(city:String) = getResult {
        apiService.getWeatherOfCity(city)
    }
}