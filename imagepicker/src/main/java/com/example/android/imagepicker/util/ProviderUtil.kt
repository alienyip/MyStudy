package com.example.android.imagepicker.util

import android.content.Context

/**
 * Created by Nature on 2017/11/30.
 */
object ProviderUtil {
    fun getFileProviderName(context: Context): String = context.packageName + ".provider"
}