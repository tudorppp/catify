package com.example.baseproject.networking

import dagger.Binds
import dagger.Module
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [RestModuleBinds::class])
object RestModule {

    @JvmStatic
    @Singleton
    internal fun provideMoshi(): Converter.Factory {
        return MoshiConverterFactory.create()
    }

    @JvmStatic
    @Singleton
    internal fun provideOkHttpClient(authenticationHeaderInterceptor: HttpAuthenticationHeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpDefaultHeaderInterceptor())
            .addInterceptor(HttpUserAgentInterceptor())
            .addInterceptor(authenticationHeaderInterceptor)
            .build()
    }

    @JvmStatic
    @Singleton
    internal fun provideRetrofit(
        converter: Converter.Factory,
        client: OkHttpClient,
        stagingDataSource: StagingDataSource
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converter)
            .client(client)
            .baseUrl(stagingDataSource.getBaseUrl())
            .build()
    }

}

@Module
internal abstract class RestModuleBinds {

    @Binds
    @Singleton
    abstract fun bindStagingDataSource(impl: StagingDataSourceImpl): StagingDataSource

}