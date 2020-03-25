package io.github.omievee.currentchallenge.sharedpref

import android.content.Context
import android.content.SharedPreferences
import io.github.omievee.currentchallenge.util.Constants

object UserPreference {

    private lateinit var sPrefs: SharedPreferences

    fun load(context: Context) {
        sPrefs = context.getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)
    }

    var isFirstRun: Boolean
        get() {
            return sPrefs.getBoolean(Constants.FIRST_RUN, true)
        }
        set(value) {
            sPrefs.edit().putBoolean(Constants.FIRST_RUN, value).apply()
        }

    var doNotShowAgain: Int
        get() {
            return sPrefs.getInt(Constants.DENIED, 0)
        }
        set(value) {
            sPrefs.edit().putInt(Constants.DENIED, value).apply()
        }

}