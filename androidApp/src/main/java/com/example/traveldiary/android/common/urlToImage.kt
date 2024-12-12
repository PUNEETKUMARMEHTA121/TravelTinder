package com.example.traveldiary.android.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.traveldiary.android.R

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, s: String?) {
    val options = RequestOptions.placeholderOf(R.drawable.empty_image).error(R.drawable.empty_image)
    Glide.with(view).setDefaultRequestOptions(options).load(s ?: "").into(view)
}