package io.github.omievee.currentchallenge.mainactivity

import io.github.omievee.currentchallenge.permissionsmanager.PermissionsManager
import io.github.omievee.currentchallenge.sharedpref.UserPreference

class BasePresenter(val view: BaseActivity, val permissions: PermissionsManager) {


    fun checkPermissions(): Boolean {
        return permissions.onCheckNecessaryPermissions()
    }

    fun requestPermissions() {
        view.onRequestPermissions()
    }


    fun onPermissionsAllowed() {
        UserPreference.isFirstRun = false
        view.onContinueToApp()
    }

    fun onShowManualPermissionsDialog() {
        view.onShowDialog()
    }
}