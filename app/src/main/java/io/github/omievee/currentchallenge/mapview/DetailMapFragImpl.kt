package io.github.omievee.currentchallenge.mapview

import com.example.BusinessDetailQuery
import com.example.YelpQuery

interface DetailMapFragImpl {
    fun onDisplayError()
    fun onUpdateMap(coordinates: BusinessDetailQuery.Coordinates)
    fun onUpdateDetails(business: BusinessDetailQuery.Business)

    fun onHideProgress()
    fun onDisplayProgress()
}
