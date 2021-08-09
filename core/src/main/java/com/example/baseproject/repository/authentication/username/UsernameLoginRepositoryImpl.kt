package com.example.baseproject.repository.authentication.username

import com.example.baseproject.repository.authentication.AuthTokenStore
import com.example.baseproject.repository.authentication.AuthenticationMethod
import com.example.baseproject.repository.authentication.LoginRepository
import io.reactivex.rxjava3.core.Completable

internal class UsernameLoginRepositoryImpl(
    private val loginRemoteDataSource: UsernameLoginRemoteDataSourceImpl,
    private val authTokenStore: AuthTokenStore
) :
    LoginRepository<AuthenticationMethod.Username> {

    override fun login(authenticationMethod: AuthenticationMethod.Username): Completable {
        return Completable.fromSingle(
            loginRemoteDataSource.login(authenticationMethod)
                .doAfterSuccess {
                    authTokenStore.storeAuthToken(it)
                }
        )
    }

    override fun logout() {

    }

}