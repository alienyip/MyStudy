package com.example.android.imagepicker.loader

import android.app.Activity
import android.widget.ImageView
import java.io.Serializable

/**
 * Created by Nature on 2017/11/29.
 */
interface ImageLoader : Serializable {
    fun displayImage(activity: Activity, path: String, imageView: ImageView, width: Int, height: Int)

    fun displayImagePreview(activity: Activity, path: String, imageView: ImageView, width: Int, height: Int)

    fun clearMemoryCache()
}