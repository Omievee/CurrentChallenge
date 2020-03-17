package io.github.omievee.currentchallenge.restaurantsmanager

import android.annotation.SuppressLint
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.cache.normalized.ApolloStoreOperation
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.rx2.Rx2Apollo
import com.example.BusinessDetailQuery
import com.example.YelpQuery
import io.github.omievee.currentchallenge.application.ChallengeApp
import io.github.omievee.currentchallenge.locationmanager.LocationManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RestaurantsManagerImpl(
    val context: ChallengeApp,
    private val apolloClient: ApolloClient,
    locationManager: LocationManager
) : RestaurantsManager {

    var searchRadius: Double = 19312.1 //12 miles approx.
    var searchTerm: String = "burritos"
    var location = locationManager.onGetLatestCoordinates()

    @SuppressLint("CheckResult")
    override fun onGetRestaurants(): Observable<Response<YelpQuery.Data>> {
        return Rx2Apollo
            .from(
                apolloClient.query(
                    YelpQuery.builder()
                        .latitude(location.lat)
                        .longitude(location.long)
                        .term(searchTerm)
                        .radius(searchRadius)
                        .build()
                )
            ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("CheckResult")
    override fun onGetRestaurantDetails(id: String): Observable<Response<BusinessDetailQuery.Data>> {
        return Rx2Apollo.from(
                apolloClient.query(
                    BusinessDetailQuery.builder()
                        .id(id)
                        .build()
                )
            ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}