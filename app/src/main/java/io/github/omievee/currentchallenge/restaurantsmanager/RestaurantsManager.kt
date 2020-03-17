package io.github.omievee.currentchallenge.restaurantsmanager

import com.apollographql.apollo.api.Response
import com.example.BusinessDetailQuery
import com.example.YelpQuery
import io.reactivex.Observable

interface RestaurantsManager {
    fun onGetRestaurants(): Observable<Response<YelpQuery.Data>>
    fun onGetRestaurantDetails(id: String): Observable<Response<BusinessDetailQuery.Data>>
}