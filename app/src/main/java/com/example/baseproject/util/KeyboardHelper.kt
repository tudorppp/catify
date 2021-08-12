package com.example.baseproject.util

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun View.closeKeyboard() {
    context.getSystemService<InputMethodManager>()
        ?.hideSoftInputFromWindow(windowToken, 0)
}