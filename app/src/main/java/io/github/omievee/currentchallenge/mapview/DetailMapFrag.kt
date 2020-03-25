package io.github.omievee.currentchallenge.mapview

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.BusinessDetailQuery
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.AndroidSupportInjection
import io.github.omievee.currentchallenge.R
import kotlinx.android.synthetic.main.fragment_business_detail_map.*
import javax.inject.Inject


private const val KEY = "id"

class DetailMapFrag : Fragment(), DetailMapFragImpl {

    @Inject
    lateinit var presenter: DetailMapFragPresenter

    private var idKey: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idKey = it.getString(KEY)
        }
        Log.d("MAP STUFF", ">>>>>>>> on Create")
        presenter.onGetRestaurantDetails(idKey ?: return)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_business_detail_map, container, false)
    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onDisplayProgress()

    }

    companion object {
        @JvmStatic
        fun newInstance(id: String) =
            DetailMapFrag().apply {
                arguments = Bundle().apply {
                    putString(KEY, id)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onDisplayError() {
        Toast.makeText(context, getString(R.string.error_loading), Toast.LENGTH_LONG).show()
        activity?.onBackPressed()
    }

    override fun onUpdateMap(coordinates: BusinessDetailQuery.Coordinates) {
        val lat = coordinates.latitude() ?: 0.0
        val long = coordinates.longitude() ?: 0.0
        val coordinates = LatLng(lat, long)
        val callback = OnMapReadyCallback { googleMap ->

            googleMap.addMarker(MarkerOptions().position(coordinates))
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    coordinates, 15f
                )
            )
            googleMap.uiSettings.isScrollGesturesEnabled = false
        }
        onHideProgress()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


    override fun onUpdateDetails(business: BusinessDetailQuery.Business) {
        businessName.text = business.name()
        businessAddress.text = business.location()?.address1()
        pricing.text = when (business.price().isNullOrEmpty()) {
            true -> getString(R.string.pricing_not_available)
            else -> business.price()
        }
        phone.text = business.phone()
    }

    override fun onHideProgress() {
        progressBar.visibility = View.GONE
    }
    override fun onDisplayProgress() {
        progressBar.visibility = View.VISIBLE
    }

}