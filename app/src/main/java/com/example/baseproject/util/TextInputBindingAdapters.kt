package com.example.baseproject.util

import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:errorText")
fun TextInputLayout.setErrorMessage(message: String?) {
    error = message
}

@BindingAdapter("app:errorText")
fun TextInputLayout.setErrorMessageGivingResId(@StringRes messageResId: Int?) {
    error = if (messageResId != null)
        context.getString(messageResId)
    else
        null
}