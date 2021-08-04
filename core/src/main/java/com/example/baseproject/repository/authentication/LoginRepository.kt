package com.example.baseproject.repository.authentication

import io.reactivex.rxjava3.core.Single

internal interface LoginRepository<AM : AuthenticationMethod> {

    fun login(authenticationMethod: AM): Single<String>

    fun logout()

}