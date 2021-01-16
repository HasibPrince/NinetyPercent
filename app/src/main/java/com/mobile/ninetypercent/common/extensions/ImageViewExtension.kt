package com.mobile.ninetypercent.common.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.mobile.ninetypercent.R
import com.mobile.ninetypercent.common.ImageLoader

/**
 * Created by Ramiz Raja on 28/05/2020
 */
@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String?) {
    ImageLoader.instance?.load(context, url ?: "", this)
}

fun ImageView.loadWithPlaceholder(url: String?, @DrawableRes placeholder: Int = R.drawable.placeholder) {
    ImageLoader.instance?.load(context, url ?: "", this, placeholder)
}

@BindingAdapter("loadResource")
fun ImageView.load(@DrawableRes resource: Int) {
    ImageLoader.instance?.load(context, resource, this)
}

@BindingAdapter("load")
fun ImageView.load(drawable: Drawable) {
    ImageLoader.instance?.load(context, drawable, this)
}