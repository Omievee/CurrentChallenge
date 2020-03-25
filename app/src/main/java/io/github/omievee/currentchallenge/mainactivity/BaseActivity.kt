package io.github.omievee.currentchallenge.mainactivity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import dagger.android.AndroidInjection
import io.github.omievee.currentchallenge.R
import io.github.omievee.currentchallenge.mainfragment.MainFragment
import io.github.omievee.currentchallenge.sharedpref.UserPreference
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class BaseActivity : ChallengeActivity(), BaseActivityImpl, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.permissionsButton -> presenter.requestPermissions()
        }
    }

    @Inject
    lateinit var presenter: BasePresenter

    private var permissionsArray: Array<String> = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private var requestCode: Int = 1
    private var manualCode: Int = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setClicks()

        if (!UserPreference.isFirstRun && presenter.checkPermissions()) {
            presenter.onPermissionsAllowed()
        }
    }

    private fun setClicks() {
        permissionsButton.setOnClickListener(this)
    }

    override fun onRequestPermissions() {
        requestPermissions(permissionsArray, requestCode)
    }

    override fun onContinueToApp() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerA, MainFragment.newInstance()).addToBackStack("main").commit()
    }

    override fun onShowDialog() {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.permissions_title))
            setCancelable(false)
            setMessage(getString(R.string.permissions_message))
        }.setPositiveButton(getString(R.string.dialog_ok)) { _, _ ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivityForResult(intent, manualCode)
        }.setNegativeButton(getString(R.string.dialog_deny)) { _, _ ->

        }.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            this.manualCode -> {
                if (presenter.checkPermissions()) {
                    presenter.onPermissionsAllowed()
                }
            }
        }
    }

    var count: Int = 0
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            this.requestCode -> {
                grantResults.forEach {

                }
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    presenter.onPermissionsAllowed()
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    ) {
                        presenter.onShowManualPermissionsDialog()
                    } else makeToast(getString(R.string.toast_required))
                }
            }
        }
    }

    fun makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
