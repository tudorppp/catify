package com.example.baseproject.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baseproject.repository.authentication.AuthManager

class RequireLoginViewModel(private val authManager: AuthManager) : ViewModel() {

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> = _loginState

    fun checkLoginState() {
        _loginState.value = authManager.getLoginStatus()
    }

}