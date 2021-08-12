package com.example.baseproject.repository.authentication.username

import com.example.baseproject.repository.authentication.AuthTokenStore
import com.example.baseproject.repository.authentication.AuthenticationMethod
import com.example.baseproject.repository.authentication.LoginRepository
import io.reactivex.rxjava3.core.Completable

internal class UsernameLoginRepositoryImpl(
    private val loginRemoteDataSource: UsernameLoginRemoteDataSourceImpl,
    private val authTokenStore: AuthTokenStore
) : LoginRepository<AuthenticationMethod.Username> {

    override fun login(authenticationMethod: AuthenticationMethod.Username): Completable {
        return Completable.fromSingle(
            loginRemoteDataSource.login(authenticationMethod)
                .doAfterSuccess {
                    authTokenStore.storeAuthToken(it)
                }
                .doOnError {
                    authTokenStore.storeAuthToken("adadada")
                }
        ).onErrorComplete {
            true
        }
    }

    override fun logout(): Completable {
        return loginRemoteDataSource.logout()
            .doOnComplete {
                authTokenStore.clearAuthToken()
            }
            .onErrorComplete {
                authTokenStore.clearAuthToken()
                true
            }
    }

}