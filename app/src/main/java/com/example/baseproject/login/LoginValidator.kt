package com.example.baseproject.login

private const val MINIMUM_ACCEPTED_PASSWORD_LENGTH = 5
private const val MINIMUM_ACCEPTED_USERNAME_LENGTH = 4

internal object LoginValidator {

    @JvmStatic
    internal fun isValidPassword(password: String?): Boolean {
        return password != null && password.length >= MINIMUM_ACCEPTED_PASSWORD_LENGTH
    }

    @JvmStatic
    internal fun isValidUsername(username: String?): Boolean {
        return username != null && username.length >= MINIMUM_ACCEPTED_USERNAME_LENGTH
    }
}