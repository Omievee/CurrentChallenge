package io.github.omievee.currentchallenge.mainfragment

import com.example.YelpQuery
import io.github.omievee.currentchallenge.restaurantsmanager.RestaurantsManager
import io.reactivex.disposables.Disposable


class MainFragPresenter(val view: MainFragment, val manager: RestaurantsManager) {

    private var restaurantsDisposable: Disposable? = null

    var businessDataList: List<YelpQuery.Business> = emptyList()
    fun getNearbyRestaurants() {
        restaurantsDisposable?.dispose()
        restaurantsDisposable = manager
            .onGetRestaurants()
            .doOnError {
                view.onHideProgress()
                view.onDisplayError()
            }
            .map {
                businessDataList = it.data()?.search()?.business() ?: emptyList()
            }
            .subscribe({
                view.updateAdapter(businessDataList)
                view.onHideProgress()
            }, {
                it.printStackTrace()
            })
    }

    fun getRestaurantDetails(restaurant: YelpQuery.Business) {
        view.displaySelectedRestaurant(restaurant)
    }

    fun onDestroy() {
        restaurantsDisposable?.dispose()
    }
}