package com.example.baseproject.feature.login

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.example.baseproject.R
import com.example.baseproject.errors.ErrorType
import com.example.baseproject.errors.asError
import com.example.baseproject.shared.BaseRxViewModel
import com.example.baseproject.shared.Event
import com.example.baseproject.usecase.LoginWithUsernameUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel(
    private val loginWithUsernameUseCase: LoginWithUsernameUseCase,
    private val resources: Resources
) : BaseRxViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _usernameErrorText = MutableLiveData<String?>(null)
    val usernameErrorText: LiveData<String?> = _usernameErrorText.distinctUntilChanged()

    private val _passwordErrorText = MutableLiveData<String?>(null)
    val passwordErrorText: LiveData<String?> = _passwordErrorText.distinctUntilChanged()

    private val _state = MutableLiveData<Event<State>>()
    val state: LiveData<Event<State>> = _state

    fun onLoginClicked() {
        if (isValidPassword() and isValidUsername()) {
            loginWithUsername(username.value ?: "", password.value ?: "")
        }
    }

    private fun isValidPassword(): Boolean {
        return if (!LoginValidator.isValidPassword(password.value)) {
            setPasswordErrorText(R.string.login_invalid_password)
            false
        } else {
            setPasswordErrorText(null)
            true
        }
    }

    private fun setPasswordErrorText(@StringRes textResId: Int?) {
        _passwordErrorText.value =
            if (textResId != null) {
                resources.getString(textResId)
            } else null
    }


    private fun isValidUsername(): Boolean {
        return if (!LoginValidator.isValidUsername(username.value)) {
            setUsernameErrorText(R.string.login_invalid_username)
            false
        } else {
            setUsernameErrorText(null)
            true
        }
    }

    private fun setUsernameErrorText(@StringRes textResId: Int?) {
        _usernameErrorText.value =
            if (textResId != null) {
                resources.getString(textResId)
            } else null
    }

    private fun loginWithUsername(username: String, password: String) {
        disposable.add(
            loginWithUsernameUseCase(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _state.value = Event(State.LoginSucceeded)
                }, {
                    val messageText = getErrorMessage(it.asError())
                    _state.value = Event(State.LoginFailed(messageText))
                })
        )
    }

    private fun getErrorMessage(errorType: ErrorType): String {
        return when (errorType) {
            is ErrorType.Api -> errorType.message ?: ""
            ErrorType.NoInternet -> resources.getString(R.string.no_internet_error)
            ErrorType.Unknown -> resources.getString(R.string.unknown_error)
        }
    }

    sealed class State {
        object LoginSucceeded : State()
        data class LoginFailed(val errorMessage: String) : State()
    }
}