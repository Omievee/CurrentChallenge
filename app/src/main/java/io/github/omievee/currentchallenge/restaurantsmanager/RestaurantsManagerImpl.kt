package io.github.omievee.currentchallenge.restaurantsmanager

import io.github.omievee.currentchallenge.application.ChallengeApp
import io.github.omievee.currentchallenge.locationmanager.LocationManager
import io.github.omievee.currentchallenge.network.SearchResult
import io.github.omievee.currentchallenge.network.YelpApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RestaurantsManagerImpl(
    val context: ChallengeApp,
    val api: YelpApi,
    val locationManager: LocationManager
) : RestaurantsManager {


    override fun onGetRestaurants(): Single<SearchResult> {
         val latestCoordinates = locationManager.onGetLatestCoordinates()
        return api
            .onGetRestaurants(
                term = "burritos",
                latitude = latestCoordinates.lat.toString(),
                longitude = latestCoordinates.long.toString()
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}