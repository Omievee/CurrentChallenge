package io.github.omievee.currentchallenge.network

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YelpApi {

    @GET("businesses/search")
    fun onGetRestaurants(
        @Query("term") term: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): Single<SearchResult>


    @GET("/businesses/{id}")
    fun onGetBusinessDetails(@Path("id") businessId: String)


}