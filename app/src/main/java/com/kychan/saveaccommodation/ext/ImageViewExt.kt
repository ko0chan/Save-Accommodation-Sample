package com.kychan.saveaccommodation.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kychan.saveaccommodation.R

fun ImageView.setImage(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .apply {
            centerCrop()
        }
        .error(R.drawable.ic_launcher_background)
        .into(this)
}