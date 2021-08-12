package com.example.baseproject.repository.authentication

internal interface AuthTokenStore {

    fun getAuthToken(): String?

    fun storeAuthToken(token: String)

    fun clearAuthToken()

    fun getAPIDefaultAuthToken() : String

}