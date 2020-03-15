package io.github.omievee.currentchallenge.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import io.github.omievee.currentchallenge.application.ChallengeApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppBindingModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ChallengeApp): Builder
        fun build(): AppComponent
    }
    fun inject(app: ChallengeApp)
}
