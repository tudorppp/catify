package com.example.baseproject.networking

import com.example.baseproject.repository.authentication.AuthTokenPreferenceStorage
import com.example.baseproject.repository.authentication.AuthTokenStore
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal val restModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(get<MoshiConverterFactory>())
            .client(get())
            .baseUrl(get<StagingDataSource>().getBaseUrl())
            .build()
    }

    single { MoshiConverterFactory.create() }

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