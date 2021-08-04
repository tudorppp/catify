package com.example.baseproject.repository.authentication

import javax.inject.Inject

internal class AuthManagerImpl @Inject constructor(private val tokenStore: AuthTokenStore) : AuthManager {

    override fun getLoginStatus(): Boolean {
        return tokenStore.getAuthToken() != null
    }
}