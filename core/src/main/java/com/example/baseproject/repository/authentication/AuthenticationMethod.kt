package com.example.baseproject.repository.authentication

internal sealed class AuthenticationMethod {
    data class Username(val userName: String, val password: String) : AuthenticationMethod()
}