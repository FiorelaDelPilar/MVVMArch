package com.example.mvvmarch.common.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mvvmarch.R

@BindingAdapter("glideUrl")
fun bindLoadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .error(R.drawable.ic_broken_image_24)
        .into(view)
}