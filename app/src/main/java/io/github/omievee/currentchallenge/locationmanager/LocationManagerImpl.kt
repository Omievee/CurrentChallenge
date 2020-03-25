package io.github.omievee.currentchallenge.locationmanager

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import io.github.omievee.currentchallenge.application.ChallengeApp
import io.github.omievee.currentchallenge.permissionsmanager.PermissionsManager

class LocationManagerImpl(val context: ChallengeApp, val permissions: PermissionsManager) :
    LocationManager {


    @SuppressLint("MissingPermission")
    override fun onGetLatestCoordinates(): CurrentLocation {
        println("PERMISSIONS>>>>>> ${permissions.onCheckNecessaryPermissions()}")
        return if (permissions.onCheckNecessaryPermissions()) {
            val lm =
                context.getSystemService(Context.LOCATION_SERVICE) as android.location.LocationManager?
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