package com.example.baseproject.repository.authentication

import com.example.baseproject.networking.service.UsernameLoginService
import com.example.baseproject.repository.authentication.username.UsernameLoginRemoteDataSourceImpl
import com.example.baseproject.repository.authentication.username.UsernameLoginRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

internal const val USERNAME_LOGIN_REPOSITORY = "usernameLoginRepository"
internal const val USERNAME_LOGIN_REMOTE_DATA_SOURCE = "usernameLoginRemoteDataSource"

internal val authDataSourceModule = module {

    single<UsernameLoginService> { get<Retrofit>().create(UsernameLoginService::class.java) }

    single<AuthManager> { AuthManagerImpl(tokenStore = get()) }

    single<AuthTokenStore> { AuthTokenPreferenceStorage(authPrefs = get()) }

    factory<LoginRepository<AuthenticationMethod.Username>>(named(USERNAME_LOGIN_REPOSITORY)) {
        UsernameLoginRepositoryImpl(
            loginRemoteDataSource = get(named(USERNAME_LOGIN_REMOTE_DATA_SOURCE)),
            authTokenStore = get()
        )
    }

    factory(named(USERNAME_LOGIN_REMOTE_DATA_SOURCE)) { UsernameLoginRemoteDataSourceImpl(usernameLoginService = get()) }
}