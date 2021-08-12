package com.example.baseproject.repository.authentication.username

import com.example.baseproject.networking.service.UsernameLoginService
import com.example.baseproject.repository.authentication.AuthenticationMethod
import com.example.baseproject.repository.authentication.LoginRemoteDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

internal class UsernameLoginRemoteDataSourceImpl(private val usernameLoginService: UsernameLoginService) :
    LoginRemoteDataSource<AuthenticationMethod.Username> {

    override fun login(authenticationMethod: AuthenticationMethod.Username): Single<String> {
        return usernameLoginService.login(authenticationMethod.userName, authenticationMethod.password)
    }

    override fun logout(): Completable {
        return usernameLoginService.logout()
    }
}