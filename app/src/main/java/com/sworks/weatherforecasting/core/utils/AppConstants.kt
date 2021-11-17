package com.sworks.weatherforecasting.core.utils

import com.sworks.weatherforecasting.BuildConfig

object AppConstants {

    const val CITY = "city"
    const val ID = "id"
    const val REQUEST_TIME_OUT: Long = 60
    const val WEATHER_UNIT = "metric"

    const val LOCALE="LOCALE"
    const val IS_FIRST="IS_FIRST"
    const val RELOAD_START="RELOAD_START"

    const val WEATHER_API_QUERY :String = "appid"
    const val BASE_URL_RETROFIT_API: String = BuildConfig.SERVER_URL
    const val WEATHER_API_KEY :String = BuildConfig.WEATHER_API_KEY

    const val MULTIPLE_LOCATION_PERMISSION = 1
    const val LOCATION_SETTINGS_REQUEST = 1
    const val BACK_PRESS_INTERVAL: Long = 3 * 1000
    const val SPLASH_TIME_OUT: Long = 4000
    const val UPDATE_INTERVAL_SECS = 10L
    const val FASTEST_UPDATE_INTERVAL_SECS = 2L
}