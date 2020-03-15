package io.github.omievee.currentchallenge.mainactivity

import dagger.Module
import dagger.Provides
import io.github.omievee.currentchallenge.di.ActivityScope

@Module
class BaseActivityModule {

    @Provides
    @ActivityScope
    fun provideVM(
        baseActivity: BaseActivity
    ): BasePresenter =
        BasePresenter(baseActivity)
}