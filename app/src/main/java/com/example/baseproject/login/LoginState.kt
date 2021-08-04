package com.example.baseproject.login

const val LOGIN_STATE = "loginState"

sealed class LoginState {
    object LoginSuccessful : LoginState()
    object LoginFailed : LoginState()
}