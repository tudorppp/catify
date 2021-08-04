package com.example.baseproject.usecase

import com.example.baseproject.repository.authentication.AuthenticationMethod
import com.example.baseproject.repository.authentication.LoginRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginWithUsernameUseCase @Inject internal constructor(
    private val loginRepository: LoginRepository<AuthenticationMethod.Username>
) {

    operator fun invoke(username: String, password: String): Single<String> {
        return loginRepository.login(AuthenticationMethod.Username(username, password))
    }
}