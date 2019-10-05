package com.revolut.testapp.util

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {

    fun loadCircularImage(@DrawableRes resourceId: Int, view: ImageView)
}
