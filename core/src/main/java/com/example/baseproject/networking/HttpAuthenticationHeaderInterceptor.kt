package com.example.baseproject.networking

import com.example.baseproject.repository.authentication.AuthTokenStore
import okhttp3.Interceptor
import okhttp3.Response

private const val HEADER_AUTHORIZATION = "Authorization"
private const val HEADER_CAT_API_KEY = "x-api-key"

internal class HttpAuthenticationHeaderInterceptor(private val tokenStorage: AuthTokenStore) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        val accessToken = tokenStorage.getAuthToken()
        if (accessToken != null && accessToken.isNotBlank()) {
            builder.addHeader(HEADER_AUTHORIZATION, accessToken)
            builder.addHeader(HEADER_CAT_API_KEY, tokenStorage.getAPIDefaultAuthToken())
        }
        return chain.proceed(builder.build())
    }
}