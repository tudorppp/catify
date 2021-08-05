package com.example.baseproject.repository.authentication

import com.example.baseproject.repository.authentication.username.UsernameLoginApi
import org.koin.dsl.module
import retrofit2.Retrofit

internal val authDataSourceModule = module {

    single<UsernameLoginApi> { get<Retrofit>().create(UsernameLoginApi::class.java) }

    single<AuthManager> { AuthManagerImpl(tokenStore = get()) }

    single<AuthTokenStore> { AuthTokenPreferenceStorage(authPrefs = get()) }
}