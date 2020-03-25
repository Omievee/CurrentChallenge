package io.github.omievee.currentchallenge.mainactivity

import dagger.Module
import dagger.Provides
import io.github.omievee.currentchallenge.di.ActivityScope
import io.github.omievee.currentchallenge.permissionsmanager.PermissionsManager

@Module
class BaseActivityModule {

    @Provides
    @ActivityScope
    fun provideVM(
        baseActivity: BaseActivity,
        permissions: PermissionsManager
    ): BasePresenter =
        BasePresenter(baseActivity, permissions)
}