package io.github.omievee.currentchallenge.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.omievee.currentchallenge.mainactivity.BaseActivity
import io.github.omievee.currentchallenge.mainactivity.BaseActivityModule
import io.github.omievee.currentchallenge.mainfragment.MainFragModule
import io.github.omievee.currentchallenge.mainfragment.MainFragment
import io.github.omievee.currentchallenge.mapview.DetailMapFrag
import io.github.omievee.currentchallenge.mapview.DetailMapFragModule
import io.github.omievee.currentchallenge.network.ApiModule


@Module(includes = [ApiModule::class])
interface AppBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseActivityModule::class])
    fun baseActivity(): BaseActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragModule::class])
    fun mainFrag(): MainFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailMapFragModule::class])
    fun mapFrag(): DetailMapFrag
}