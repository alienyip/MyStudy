package com.example.android.imagepicker.bean

import java.io.Serializable

/**
 * Created by Nature on 2017/11/27.
 */
data class ImageFolder (var name: String?,
                        var path: String?,
                        var cover: ImageItem? = null,
                        var images: ArrayList<ImageItem> = ArrayList()
) : Serializable{

    override fun equals(other: Any?): Boolean {
        try {
            val item = other as ImageFolder?
            return this.path!!.equals(item!!.path!!, ignoreCase = true)
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return path?.hashCode() ?: 0
    }
}