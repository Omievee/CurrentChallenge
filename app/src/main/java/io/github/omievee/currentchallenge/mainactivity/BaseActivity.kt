package io.github.omievee.currentchallenge.mainactivity

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import io.github.omievee.currentchallenge.R
import io.github.omievee.currentchallenge.mainfragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class BaseActivity : ChallengeActivity(), BaseActivityImpl, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.permissionsButton -> presenter.onRequestPermissions(this)
        }
    }

    @Inject
    lateinit var presenter: BasePresenter

    private var permissionsArray: Array<String> = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    private var requestCode: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setClicks()

    }

    private fun setClicks() {
        permissionsButton.setOnClickListener(this)
    }


    override fun onRequestPermissions() {
        requestPermissions(permissionsArray, requestCode)
    }

    override fun onContinueToApp() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerA, MainFragment.newInstance()).commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            this.requestCode -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    onContinueToApp()
                } else Toast.makeText(
                    this,
                    getString(R.string.toast_required),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


}
