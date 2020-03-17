package io.github.omievee.currentchallenge.di

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import io.github.omievee.currentchallenge.application.ChallengeApp
import io.github.omievee.currentchallenge.locationmanager.LocationManager
import io.github.omievee.currentchallenge.locationmanager.LocationManagerImpl
import io.github.omievee.currentchallenge.restaurantsmanager.RestaurantsManager
import io.github.omievee.currentchallenge.restaurantsmanager.RestaurantsManagerImpl
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideLocationManager(
        context: ChallengeApp
    ): LocationManager {
        return LocationManagerImpl(
            context
        )
    }

    @Provides
    @Singleton
    fun provideRestaurantsManager(
        context: ChallengeApp,
        api: ApolloClient,
        locationManager: LocationManager
    ): RestaurantsManager {
        return RestaurantsManagerImpl(
            context,
            api,
            locationManager
        )
    }

}