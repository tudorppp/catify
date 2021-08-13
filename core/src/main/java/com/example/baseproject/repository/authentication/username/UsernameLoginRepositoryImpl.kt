package com.example.baseproject.repository.authentication.username

import com.example.baseproject.repository.authentication.AuthTokenStore
import com.example.baseproject.repository.authentication.AuthenticationMethod
import com.example.baseproject.repository.authentication.LoginRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.*

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
                .handleErrorsOnLogin()
        ).onErrorComplete()
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

    //TODO this method is temporarily returning a random token until the backend is ready
    private fun Single<String>.handleErrorsOnLogin(): Single<String> {
        return doOnError {
            authTokenStore.storeAuthToken(UUID.randomUUID().toString())
            onErrorComplete()
        }
    }

}