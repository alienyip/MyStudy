package com.example.android.imagepicker.util

import android.app.Activity
import android.content.Context
import android.os.Environment
import android.util.DisplayMetrics
import android.util.TypedValue

/**
 * Created by Nature on 2017/11/29.
 */
object Utils {

    //根据屏幕宽度与密度计算GridView显示的列数， 最少为三列，并获取Item宽度
    @JvmStatic
    fun getImageItemWidth(activity: Activity): Int {
        val screenWidth = activity.resources.displayMetrics.widthPixels
        val densityDpi = activity.resources.displayMetrics.densityDpi
        var cols = screenWidth / densityDpi
        cols = if (cols < 3) 3 else cols
        val columnSpace = (2 * activity.resources.displayMetrics.density).toInt()
        return (screenWidth - columnSpace * (cols - 1)) / cols
    }

    //判断SDCard是否可用
    @JvmStatic
    fun existSDCard(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    //获取手机分辨率
    @JvmStatic
    fun getScreenPix(activity: Activity): DisplayMetrics {
        val displaysMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displaysMetrics)
        return displaysMetrics
    }

    //dp转px
    @JvmStatic
    fun dp2px(context: Context, dpVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.resources.displayMetrics).toInt()
    }
}