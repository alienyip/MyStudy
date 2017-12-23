package com.example.android.imagepicker.util

import android.content.Context
import java.io.File

/**
 * Created by Nature on 2017/12/11.
 */
object FileUtil {
    fun getCropCacheFolder(context: Context): File {
        return File(context.cacheDir.toString() + "/ImagePicker/cropTemp")
    }
}