package io.github.omievee.currentchallenge.network

import io.github.omievee.currentchallenge.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        requestBuilder.apply {
            header(
                Constants.Authorization,
                "Bearer ${Constants.key}"
            )
        }
        return chain.proceed(requestBuilder.build())
    }
}