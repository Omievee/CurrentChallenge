package io.github.omievee.currentchallenge.clicklistener

import com.example.YelpQuery

interface RestaurantClickListener {
    fun onRestaurantClicked(id: YelpQuery.Business)
}