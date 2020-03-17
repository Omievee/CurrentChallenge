package io.github.omievee.currentchallenge.network

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import io.github.omievee.currentchallenge.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient {
        val httpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .addNetworkInterceptor(AuthenticationInterceptor())
                .build()
        }
        return httpClient
    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(Constants.base_url)
            .okHttpClient(client)
            .build()
    }


    @Provides
    @Singleton
    fun provideAuthenticatedRequestInterceptor(): AuthenticationInterceptor {
        return AuthenticationInterceptor()
    }
}