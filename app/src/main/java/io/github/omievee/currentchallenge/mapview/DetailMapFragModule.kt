package io.github.omievee.currentchallenge.mapview

import dagger.Module
import dagger.Provides
import io.github.omievee.currentchallenge.di.FragmentScope
import io.github.omievee.currentchallenge.restaurantsmanager.RestaurantsManager

@Module
class DetailMapFragModule {

    @Provides
    @FragmentScope
    fun providePresenter(
        fragment: DetailMapFrag, manager: RestaurantsManager
    ): DetailMapFragPresenter =
        DetailMapFragPresenter(fragment, manager)
}
