package com.example.mystudy.imagePicker

import android.app.Application
import com.example.android.imagepicker.ImagePicker

/**
 * Created by Nature on 2017/12/13.
 */
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        ImagePicker.init(GlideImageLoader())
        //保存为自定义默认
        ImagePicker.limit(12).isCrop(true).saveAsDefault()
    }
}