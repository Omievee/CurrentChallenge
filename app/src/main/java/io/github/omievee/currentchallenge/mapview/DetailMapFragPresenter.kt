package io.github.omievee.currentchallenge.mapview

import io.github.omievee.currentchallenge.restaurantsmanager.RestaurantsManager
import io.reactivex.disposables.Disposable

class DetailMapFragPresenter(val view: DetailMapFrag, val manager: RestaurantsManager) {

    var detailDisposable: Disposable? = null
    fun onGetRestaurantDetails(businessId: String) {
        detailDisposable?.dispose()

        detailDisposable = manager
            .onGetRestaurantDetails(businessId)
            .doOnError {
                view.onHideProgress()
                view.onDisplayError()
                it.printStackTrace()
            }
            .subscribe({
                view.onHideProgress()
                view.onUpdateMap(it.data()?.business()?.coordinates() ?: return@subscribe)
                view.onUpdateDetails(it.data()?.business() ?: return@subscribe)
            }, {
                it.printStackTrace()
            })
    }


    fun onDestroy() {
        detailDisposable?.dispose()
    }

}