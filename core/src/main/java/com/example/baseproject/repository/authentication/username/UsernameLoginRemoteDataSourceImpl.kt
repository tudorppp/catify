package com.example.baseproject.repository.authentication.username

import com.example.baseproject.repository.authentication.AuthenticationMethod
import com.example.baseproject.repository.authentication.LoginRemoteDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

internal class UsernameLoginRemoteDataSourceImpl @Inject constructor(private val usernameLoginApi: UsernameLoginApi) :
    LoginRemoteDataSource<AuthenticationMethod.Username> {

    override fun login(authenticationMethod: AuthenticationMethod.Username): Single<String> {
        return usernameLoginApi.login(authenticationMethod.userName, authenticationMethod.password)
    }

}