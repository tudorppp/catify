package com.example.baseproject.usecase

import com.example.baseproject.repository.authentication.AuthenticationMethod
import com.example.baseproject.repository.authentication.LoginRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class LoginWithUsernameUseCase internal constructor(private val loginRepository: LoginRepository<AuthenticationMethod.Username>) {

    operator fun invoke(username: String, password: String): Completable {
        return loginRepository.login(AuthenticationMethod.Username(username, password))
    }
}