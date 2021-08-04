package com.example.baseproject.repository.authentication

import com.example.baseproject.repository.authentication.username.UsernameLoginApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [AuthDataSourceBinds::class])
object AuthDataSourcesModule {

    @JvmStatic
    @Provides
    internal fun provideLoginApi(retrofit: Retrofit): UsernameLoginApi {
        return retrofit.create(UsernameLoginApi::class.java)
    }

}

@Module
internal abstract class AuthDataSourceBinds {

    @Binds
    @Singleton
    abstract fun bindAuthManager(impl: AuthManagerImpl): AuthManager

    @Binds
    @Singleton
    abstract fun bindAuthTokenStore(impl : AuthTokenPreferenceStorage) : AuthTokenStore

}