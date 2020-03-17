package io.github.omievee.currentchallenge.mainactivity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class BasePresenter(val view: BaseActivity) {

    fun onRequestPermissions() {
        view.onRequestPermissions()
    }


    fun checkPermissions(context: Activity) {
        if (permissionsAllowed(context)) {
            view.onContinueToApp()
        } else view.onRequestPermissions()
    }


    private fun permissionsAllowed(context: Activity): Boolean {
        return !(ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED)
    }

}