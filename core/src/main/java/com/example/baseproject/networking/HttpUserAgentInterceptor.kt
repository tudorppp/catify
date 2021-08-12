package com.example.baseproject.networking

import okhttp3.Interceptor
import okhttp3.Response

private const val HEADER_USER_AGENT = "User-Agent"
private const val SEPARATOR = "|"

internal class HttpUserAgentInterceptor : Interceptor {

    private val userAgent = listOf("Catify", "Android")
        .joinToString(separator = SEPARATOR)

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader(HEADER_USER_AGENT, userAgent)
                .build()
        )
    }
}