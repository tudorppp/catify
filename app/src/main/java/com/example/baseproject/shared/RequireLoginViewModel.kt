package com.example.baseproject.shared

import androidx.lifecycle.ViewModel
import com.example.baseproject.repository.authentication.AuthManager

class RequireLoginViewModel(private val authManager: AuthManager) : ViewModel() {

    fun isUserLoggedIn()  = authManager.getLoginStatus()

}