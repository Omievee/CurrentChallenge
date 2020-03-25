package io.github.omievee.currentchallenge.permissionsmanager

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import io.github.omievee.currentchallenge.application.ChallengeApp

class PermissionsImpl(val context: ChallengeApp) : PermissionsManager {
    override fun onCheckNecessaryPermissions(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }
}