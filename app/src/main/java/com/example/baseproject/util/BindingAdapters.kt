package com.example.baseproject.util

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter


@BindingAdapter(
    "marginStartSystemWindowInsets",
    "marginEndSystemWindowInsets",
    "marginTopSystemWindowInsets",
    "marginBottomSystemWindowInsets",
    requireAll = false
)
fun View.applySystemWindows(applyStart: Boolean, applyEnd: Boolean, applyTop: Boolean, applyBottom: Boolean) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        v.updateLayoutParams<ConstraintLayout.LayoutParams> {
            if (applyStart) this.leftMargin = insets.systemWindowInsetLeft
            if (applyEnd) this.rightMargin = insets.systemWindowInsetRight
            if (applyTop) this.topMargin = insets.systemWindowInsetTop
            if (applyBottom) this.bottomMargin = insets.systemWindowInsetBottom
        }
        insets
    }
}