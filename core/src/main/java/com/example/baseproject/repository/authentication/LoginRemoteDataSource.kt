package com.example.baseproject.repository.authentication

import io.reactivex.rxjava3.core.Single

internal interface LoginRemoteDataSource<AM : AuthenticationMethod> {

    fun login(authenticationMethod: AM): Single<String>

}