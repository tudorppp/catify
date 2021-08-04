package com.example.baseproject.networking

import okhttp3.Interceptor
import okhttp3.Response

private const val HEADER_CONTENT_TYPE = "Content-Type"
private const val HTTP_POST = "POST"
private const val HTTP_PUT = "PUT"
private const val HEADER_ACCEPT = "Accept"
private const val HEADER_MEDIA_TYPE_JSON = "application/json"

internal class HttpDefaultHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        if (request.header(HEADER_ACCEPT) == null) {
            builder.addHeader(HEADER_ACCEPT, HEADER_MEDIA_TYPE_JSON)
        }

        if ((request.method.equals(HTTP_POST, true) || request.method.equals(HTTP_PUT, true)) &&
            request.header(HEADER_CONTENT_TYPE) == null
        ) {
            builder.addHeader(HEADER_CONTENT_TYPE, HEADER_MEDIA_TYPE_JSON)
        }
        return chain.proceed(builder.build())
    }
}