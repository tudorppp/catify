package com.example.baseproject.util.binding_adapter

import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("setChips")
fun ChipGroup.setErrorMessage(texts: List<String>?) {
    texts?.forEach {
        val chip = Chip(context).apply {
            text = it
        }
        addView(chip)
    }
}