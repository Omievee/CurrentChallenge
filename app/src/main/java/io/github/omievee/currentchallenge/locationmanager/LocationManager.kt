package io.github.omievee.currentchallenge.locationmanager

interface LocationManager {

    fun onGetLatestCoordinates(): CurrentLocation
}