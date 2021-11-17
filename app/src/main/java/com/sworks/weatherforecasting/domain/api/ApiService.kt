package com.sworks.weatherforecasting.domain.api

import com.sworks.weatherforecasting.core.utils.AppConstants
import com.sworks.weatherforecasting.domain.model.WeatherCityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getWeatherOfCity(
        @Query("q") q: String,
        @Query("units") units: String = AppConstants.WEATHER_UNIT,
        @Query("appid") appid: String = AppConstants.WEATHER_API_KEY
    ): Response<WeatherCityResponse>

  @GET("weather")
  suspend fun getWeatherOfLatLon(
      @Query("lat") latitude: String,
      @Query("lon") longitude: String,
      @Query("units") units: String = AppConstants.WEATHER_UNIT,
      @Query("appid") appid: String = AppConstants.WEATHER_API_KEY
  ): Response<WeatherCityResponse>

}