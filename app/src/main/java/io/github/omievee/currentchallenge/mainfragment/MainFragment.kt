package io.github.omievee.currentchallenge.mainfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.YelpQuery
import dagger.android.support.AndroidSupportInjection
import io.github.omievee.currentchallenge.R
import io.github.omievee.currentchallenge.clicklistener.RestaurantClickListener
import io.github.omievee.currentchallenge.listview.BurritoAdapter
import io.github.omievee.currentchallenge.mapview.DetailMapFrag
import kotlinx.android.synthetic.main.fragment_blank.*
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


    private val restaurantClickListener = object : RestaurantClickListener {
        override fun onRestaurantClicked(restaurant: YelpQuery.Business) {
            presenter.getRestaurantDetails(restaurant)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onDisplayProgress()
        onGetRestaurants()


        restaurantsRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(
            context ?: return, R.drawable.divider
        )?.let {
            itemDecorator.setDrawable(
                it
            )
        }
        restaurantsRecycler.addItemDecoration(itemDecorator)
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

    override fun updateAdapter(list: List<YelpQuery.Business>) {
        error.visibility = View.GONE
        val burritoAdapter = BurritoAdapter(list, restaurantClickListener)
        restaurantsRecycler.adapter = burritoAdapter
    }

    override fun onDisplayError() {
        restaurantsRecycler.visibility = View.GONE
        error.visibility = View.VISIBLE
    }

    override fun displaySelectedRestaurant(restaurant: YelpQuery.Business) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.containerB, DetailMapFrag.newInstance(restaurant.id().toString()))
            ?.addToBackStack("map")
            ?.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}

