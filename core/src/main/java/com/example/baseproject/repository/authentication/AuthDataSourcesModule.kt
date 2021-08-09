package com.example.baseproject.repository.authentication

import com.example.baseproject.repository.authentication.username.UsernameLoginApi
import com.example.baseproject.repository.authentication.username.UsernameLoginRemoteDataSourceImpl
import com.example.baseproject.repository.authentication.username.UsernameLoginRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

internal const val USERNAME_LOGIN_REPOSITORY = "usernameLoginRepository"
internal const val USERNAME_LOGIN_REMOTE_DATA_SOURCE = "usernameLoginRemoteDataSource"

internal val authDataSourceModule = module {

    single<UsernameLoginApi> { get<Retrofit>().create(UsernameLoginApi::class.java) }

    single<AuthManager> { AuthManagerImpl(tokenStore = get()) }

    single<AuthTokenStore> { AuthTokenPreferenceStorage(authPrefs = get()) }

    factory<LoginRepository<AuthenticationMethod.Username>>(named(USERNAME_LOGIN_REPOSITORY)) {
        UsernameLoginRepositoryImpl(
            loginRemoteDataSource = get(named(USERNAME_LOGIN_REMOTE_DATA_SOURCE)),
            authTokenStore = get()
        )
    }

    factory(named(USERNAME_LOGIN_REMOTE_DATA_SOURCE)) { UsernameLoginRemoteDataSourceImpl(usernameLoginApi = get()) }
}