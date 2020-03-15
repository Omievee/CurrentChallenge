package io.github.omievee.currentchallenge.mainfragment

import io.github.omievee.currentchallenge.restaurantsmanager.RestaurantsManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


class MainFragPresenter(val view: MainFragment, val manager: RestaurantsManager) {

    var disposable: Disposable? = null
    operator fun CompositeDisposable.plusAssign(disposable: Disposable?) {
        this.add(disposable!!)
    }

    fun getNearbyRestaurants() {

        disposable?.dispose()
        disposable = manager
            .onGetRestaurants()
            .subscribe({

            }, {
                it.printStackTrace()
            })
    }
}