package com.example.baseproject.repository.authentication.username

import com.example.baseproject.repository.authentication.AuthenticationMethod
import com.example.baseproject.repository.authentication.LoginRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

internal class UsernameLoginRepositoryImpl @Inject constructor(private val loginRemoteDataSource: UsernameLoginRemoteDataSourceImpl) :
    LoginRepository<AuthenticationMethod.Username> {

    override fun login(authenticationMethod: AuthenticationMethod.Username): Single<String> {
        return loginRemoteDataSource.login(authenticationMethod)
    }

    override fun logout() {

    }

}