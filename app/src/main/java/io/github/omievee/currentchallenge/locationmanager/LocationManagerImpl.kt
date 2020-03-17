package io.github.omievee.currentchallenge.locationmanager

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import io.github.omievee.currentchallenge.application.ChallengeApp

class LocationManagerImpl(val context: ChallengeApp) : LocationManager {


    override fun onGetLatestCoordinates(): CurrentLocation {
        val lm =
            context.getSystemService(Context.LOCATION_SERVICE) as android.location.LocationManager?
        return if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val location = lm?.getLastKnownLocation(android.location.LocationManager.GPS_PROVIDER)
            val longitude: Double = location?.longitude ?: 0.0
            val latitude: Double = location?.latitude ?: 0.0
            CurrentLocation(latitude, longitude)
        } else {
            CurrentLocation(0.0, 0.0)
        }


    }
}

class CurrentLocation(val lat: Double, val long: Double)