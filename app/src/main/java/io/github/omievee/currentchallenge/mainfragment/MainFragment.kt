package io.github.omievee.currentchallenge.mainfragment

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import io.github.omievee.currentchallenge.R
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.progress.*
import javax.inject.Inject


class MainFragment : Fragment(), MainFragImpl {


    @Inject
    lateinit var presenter: MainFragPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onDisplayProgress()
        onGetRestaurants()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onDisplayProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun onGetRestaurants() {
        presenter.getNearbyRestaurants()
    }
}

