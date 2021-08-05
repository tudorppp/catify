package com.example.baseproject.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baseproject.usecase.LoginWithUsernameUseCase

class LoginViewModel(private val loginWithUsernameUseCase: LoginWithUsernameUseCase) : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun loginWithUsername(username: String, password: String) {
        loginWithUsernameUseCase(username, password)
    }

}