package io.github.omievee.currentchallenge.restaurantsmanager

import io.github.omievee.currentchallenge.network.SearchResult
import io.reactivex.Single

interface RestaurantsManager {

    fun onGetRestaurants(): Single<SearchResult>
}