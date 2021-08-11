package com.example.baseproject.networking

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal val restModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava3CallAdapterFactory>())
            .client(get())
            .baseUrl(get<StagingDataSource>().getBaseUrl())
            .build()
    }

    single<Gson> {
        GsonBuilder().create()
    }

    single<GsonConverterFactory> { GsonConverterFactory.create(get<Gson>()) }

    single { RxJava3CallAdapterFactory.create() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpDefaultHeaderInterceptor>())
            .addInterceptor(get<HttpUserAgentInterceptor>())
            .addInterceptor(get<HttpAuthenticationHeaderInterceptor>())
            .build()
    }

    single { HttpDefaultHeaderInterceptor() }

    single { HttpUserAgentInterceptor() }

    single { HttpAuthenticationHeaderInterceptor(get()) }

    single<StagingDataSource> { StagingDataSourceImpl() }
}