package com.turk.core.extensions

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.turk.core.R

@BindingAdapter("loadImage")
fun ImageView.setImage(url: String?) {

    val options = RequestOptions()
        .timeout(10000)
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    try {
        Glide.with(context)
            .load(url)
            .apply(options)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.error)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .skipMemoryCache(true)
            .into(this)
    } catch (ex: Exception) {
        circularProgressDrawable.stop()
        ex.printStackTrace()
    }
}