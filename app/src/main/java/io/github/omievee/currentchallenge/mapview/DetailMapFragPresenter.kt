package io.github.omievee.currentchallenge.mapview

import android.util.Log
import com.example.BusinessDetailQuery
import io.github.omievee.currentchallenge.restaurantsmanager.RestaurantsManager
import io.reactivex.disposables.Disposable
import okhttp3.Response

class DetailMapFragPresenter(val view: DetailMapFrag, val manager: RestaurantsManager) {

    var detailDisposable: Disposable? = null

    var coordinates: BusinessDetailQuery.Coordinates? = null
    var data: BusinessDetailQuery.Business? = null
    fun onGetRestaurantDetails(businessId: String) {
        detailDisposable?.dispose()
        detailDisposable = manager
            .onGetRestaurantDetails(businessId)
            .doOnError {
                view.onHideProgress()
                view.onDisplayError()
                it.printStackTrace()
            }
            .doOnComplete {
                view.onUpdateMap(coordinates ?: return@doOnComplete)
            }
            .map {
                coordinates = it.data()?.business()?.coordinates()
                data = it.data()?.business()
            }
            .subscribe({

            }, {
                it.printStackTrace()
            })
    }

    fun onDestroy() {
        detailDisposable?.dispose()
    }

    fun onShowDetails() {
        data?.let { view.onUpdateDetails(it) }
    }


}