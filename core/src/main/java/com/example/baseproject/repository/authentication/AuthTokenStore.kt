package com.example.baseproject.repository.authentication

internal interface AuthTokenStore {

    //TODO add some RxJava here

    fun getAuthToken(): String?

    fun storeAuthToken(token: String)

    fun clearAuthToken()

    fun getAPIDefaultAuthToken() : String

}