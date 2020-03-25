package io.github.omievee.currentchallenge.di

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import io.github.omievee.currentchallenge.application.ChallengeApp
import io.github.omievee.currentchallenge.locationmanager.LocationManager
import io.github.omievee.currentchallenge.locationmanager.LocationManagerImpl
import io.github.omievee.currentchallenge.permissionsmanager.PermissionsImpl
import io.github.omievee.currentchallenge.permissionsmanager.PermissionsManager
import io.github.omievee.currentchallenge.restaurantsmanager.RestaurantsManager
import io.github.omievee.currentchallenge.restaurantsmanager.RestaurantsManagerImpl
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideLocationManager(
        context: ChallengeApp,
        permission: PermissionsManager
    ): LocationManager {
        return LocationManagerImpl(
            context, permission
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

    @Provides
    @Singleton
    fun providePermissionsManager(
        context: ChallengeApp
    ): PermissionsManager {
        return PermissionsImpl(
            context
        )
    }


}