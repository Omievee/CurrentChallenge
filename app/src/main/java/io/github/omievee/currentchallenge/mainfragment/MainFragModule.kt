package io.github.omievee.currentchallenge.mainfragment

import dagger.Module
import dagger.Provides
import io.github.omievee.currentchallenge.di.FragmentScope
import io.github.omievee.currentchallenge.restaurantsmanager.RestaurantsManager

@Module
class MainFragModule {

    @Provides
    @FragmentScope
    fun providePresenter(
        fragment: MainFragment, manager: RestaurantsManager
    ): MainFragPresenter =
        MainFragPresenter(fragment, manager)
}
