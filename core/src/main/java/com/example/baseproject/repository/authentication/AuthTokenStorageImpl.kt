package com.example.baseproject.repository.authentication

import android.content.SharedPreferences
import javax.inject.Inject

internal const val KEY_AUTH_TOKEN = "AUTH_TOKEN"

internal class AuthTokenPreferenceStorage @Inject constructor(private val authPrefs: SharedPreferences) :
    AuthTokenStore {

    override fun getAuthToken(): String? {
        return authPrefs.getString(KEY_AUTH_TOKEN, null)
    }

    override fun storeAuthToken(token: String) {
        authPrefs
            .edit()
            .putString(KEY_AUTH_TOKEN, token)
            .apply()
        //TODO broadcast?
    }

    override fun clearAuthToken() {
        authPrefs
            .edit()
            .remove(KEY_AUTH_TOKEN)
            .apply()
        //TODO braodcast?
    }

    //TODO sup with this ? where should it be added?
    override fun getAPIDefaultAuthToken(): String {
        return "f0bc032d-df28-4c62-81b5-ca74aa735164"
    }

}