package com.example.baseproject.repository.authentication

import com.example.baseproject.repository.authentication.username.UsernameLoginApi
import com.example.baseproject.repository.authentication.username.UsernameLoginRemoteDataSourceImpl
import com.example.baseproject.repository.authentication.username.UsernameLoginRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

internal val authDataSourceModule = module {

    single<UsernameLoginApi> { get<Retrofit>().create(UsernameLoginApi::class.java) }

    single<AuthManager> { AuthManagerImpl(tokenStore = get()) }

    single<AuthTokenStore> { AuthTokenPreferenceStorage(authPrefs = get()) }

    factory<LoginRepository<AuthenticationMethod.Username>>(named("usernameLoginRepository")) { UsernameLoginRepositoryImpl(loginRemoteDataSource = get(named("usernameLoginRemoteDataSource"))) }

    factory<UsernameLoginRemoteDataSourceImpl<Any?>>(named("usernameLoginRemoteDataSource")) { UsernameLoginRemoteDataSourceImpl<Any?>(usernameLoginApi = get()) }
}