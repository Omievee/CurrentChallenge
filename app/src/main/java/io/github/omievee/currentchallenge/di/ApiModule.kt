package io.github.omievee.currentchallenge.di

import android.provider.SyncStateContract
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.github.omievee.currentchallenge.BuildConfig
import io.github.omievee.currentchallenge.network.AuthenticationInterceptor
import io.github.omievee.currentchallenge.network.YelpApi
import io.github.omievee.currentchallenge.util.Constants
import okhttp3.Cache
import okhttp3.CookieJar
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            addInterceptor(AuthenticationInterceptor())
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            this.addInterceptor(logging)
            connectTimeout(1, TimeUnit.MINUTES)
            writeTimeout(1, TimeUnit.MINUTES)
            readTimeout(1, TimeUnit.MINUTES)
        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()

    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient.Builder, gson: Gson): YelpApi {
        return Retrofit.Builder()
            .baseUrl(Constants.base_url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client.build())
            .build()
            .create(YelpApi::class.java)
    }


    @Provides
    @Singleton
    fun provideAuthenticatedRequestInterceptor(): AuthenticationInterceptor {
        return AuthenticationInterceptor()
    }
}