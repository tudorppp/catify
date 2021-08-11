package com.example.baseproject.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.baseproject.R

@BindingAdapter("app:loadImage")
fun ImageView.load(url: String?) {
    Glide
        .with(this.context)
        .load(url)
        .placeholder(R.drawable.cat_placeholder)
        .centerCrop()
        .into(this)
}