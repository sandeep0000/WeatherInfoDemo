package com.sworks.weatherforecasting.di.modules

import android.app.Application
import com.sworks.weatherforecasting.ui.home.repo.HomeRepository
import com.sworks.weatherforecasting.ui.home.repo.HomeRepositoryImp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.sworks.weatherforecasting.domain.api.ApiService
import com.sworks.weatherforecasting.ui.home.data.LocationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideLocationProviderClient(application: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application.applicationContext)
    }

    @Provides
    fun provideLocationProvider(context: Application, client: FusedLocationProviderClient
    ): LocationProvider {
        return LocationProvider(context.applicationContext, client)
    }

    @Provides
    fun providesHomeRepository(homeRepositoryImp: HomeRepositoryImp, locationProvider: LocationProvider): HomeRepository {
        return HomeRepository(homeRepositoryImp , locationProvider)
    }

    @Provides
    fun providesHomeRepositoryImp(apiService: ApiService): HomeRepositoryImp {
        return HomeRepositoryImp(apiService)
    }
}