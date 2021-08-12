package com.example.baseproject.repository.authentication

import io.reactivex.rxjava3.core.Completable

internal interface LoginRepository<AM : AuthenticationMethod> {

    fun login(authenticationMethod: AM): Completable

    fun logout(): Completable

}