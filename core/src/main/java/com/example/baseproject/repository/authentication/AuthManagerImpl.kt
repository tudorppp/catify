package com.example.baseproject.repository.authentication

internal class AuthManagerImpl(private val tokenStore: AuthTokenStore) : AuthManager {

    override fun getLoginStatus(): Boolean {
        return tokenStore.getAuthToken() != null
    }
}