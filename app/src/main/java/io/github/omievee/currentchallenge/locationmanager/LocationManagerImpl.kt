package io.github.omievee.currentchallenge.locationmanager

import android.annotation.SuppressLint
import android.content.Context
import io.github.omievee.currentchallenge.application.ChallengeApp

class LocationManagerImpl(val context: ChallengeApp) : LocationManager {


    @SuppressLint("MissingPermission")
    override fun onGetLatestCoordinates(): CurrentLocation {
        val lm =
            context.getSystemService(Context.LOCATION_SERVICE) as android.location.LocationManager?
        val location = lm?.getLastKnownLocation(android.location.LocationManager.GPS_PROVIDER)
        val longitude: Double = location?.longitude ?: 0.0
        val latitude: Double = location?.latitude ?: 0.0

        return CurrentLocation(latitude, longitude)
    }
}

class CurrentLocation(val lat: Double, val long: Double)