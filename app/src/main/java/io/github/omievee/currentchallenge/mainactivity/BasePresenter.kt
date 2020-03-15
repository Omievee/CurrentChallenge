package io.github.omievee.currentchallenge.mainactivity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class BasePresenter(val view: BaseActivity) {


    fun onRequestPermissions(context: Activity) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            view.onRequestPermissions()
        } else {
            view.onContinueToApp()
        }
    }
}