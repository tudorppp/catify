package com.example.baseproject.networking

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

internal val restModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(get<MoshiConverterFactory>())
            .addCallAdapterFactory(get<RxJava3CallAdapterFactory>())
            .client(get())
            .baseUrl(get<StagingDataSource>().getBaseUrl())
            .build()
    }

    single { MoshiConverterFactory.create() }

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