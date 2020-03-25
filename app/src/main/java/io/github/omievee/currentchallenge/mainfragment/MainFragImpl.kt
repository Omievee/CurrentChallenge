package io.github.omievee.currentchallenge.mainfragment

import com.example.YelpQuery

interface MainFragImpl {

    fun onDisplayProgress()
    fun onHideProgress()
    fun onGetRestaurants()
    fun updateAdapter(list: List<YelpQuery.Business>)
    fun onDisplayError()
    fun displaySelectedRestaurant(restaurant: YelpQuery.Business)
    fun onUserRevokedPermissions()

}