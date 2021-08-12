package com.example.baseproject.util

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import java.util.*

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

@SuppressLint("SetTextI18n")
@BindingAdapter("app:countryText")
fun TextView.setCountryWithFlag(country: String?) {
    text = "${country?.getCountryFlag()} ${country?.getCountryName()}"
}

private fun String.getCountryFlag(): String {
    val flagOffset = 0x1F1E6
    val asciiOffset = 0x41

    val firstChar = Character.codePointAt(this, 0) - asciiOffset + flagOffset
    val secondChar = Character.codePointAt(this, 1) - asciiOffset + flagOffset
    return String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))
}

private fun String.getCountryName(): String {
    return Locale("", this).displayCountry
}