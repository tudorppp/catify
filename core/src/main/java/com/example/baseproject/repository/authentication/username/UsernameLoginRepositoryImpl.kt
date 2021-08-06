package com.example.baseproject.repository.authentication.username

import com.example.baseproject.repository.authentication.AuthenticationMethod
import com.example.baseproject.repository.authentication.LoginRepository
import io.reactivex.rxjava3.core.Single

internal class UsernameLoginRepositoryImpl(private val loginRemoteDataSource: UsernameLoginRemoteDataSourceImpl<Any?>) :
    LoginRepository<AuthenticationMethod.Username> {

    override fun login(authenticationMethod: AuthenticationMethod.Username): Single<String> {
        return loginRemoteDataSource.login(authenticationMethod)
    }

    override fun logout() {

    }

}