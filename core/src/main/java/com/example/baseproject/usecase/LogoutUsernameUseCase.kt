package com.example.baseproject.usecase

import com.example.baseproject.repository.authentication.AuthenticationMethod
import com.example.baseproject.repository.authentication.LoginRepository
import io.reactivex.rxjava3.core.Completable

class LogoutUsernameUseCase internal constructor(private val loginRepository: LoginRepository<AuthenticationMethod.Username>) {

    operator fun invoke(): Completable {
        return loginRepository.logout()
    }

}