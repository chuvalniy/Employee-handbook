package com.example.kode_test_app.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kode_test_app.R

object ImageLoader{

    fun loadImage(url: String, container: ImageView) {

        Glide.with(container.context)
            .asBitmap()
            .placeholder(R.drawable.plug)
            .circleCrop()
            .load(url)
            .into(container)
    }
}