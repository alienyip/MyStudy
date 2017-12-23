package com.example.android.imagepicker

import android.content.Context
import android.support.constraint.solver.widgets.Rectangle
import com.example.android.imagepicker.bean.ImageItem
import com.example.android.imagepicker.loader.ImageLoader
import com.example.android.imagepicker.ui.ShadowActivity
import com.example.android.imagepicker.view.CropImageView

/**
 * Created by Nature on 2017/11/29.
 */
object ImagePicker {
    init {
        println("imagePicker init ...")
    }

    internal var imageLoader: ImageLoader by InitializationCheck("imageLoader is not initialized, please call 'ImagePicker.init(XX)' in your application's onCreate")

    internal var pickHelper: PickHelper = PickHelper()

    private var customPickHelper: PickHelper? = null

    internal var listener: ImagePicker.OnPickImageResultListener? = null

    //在Application中初始化图片加载框架
    @JvmStatic
    fun init(imageLoader: ImageLoader) {
        this.imageLoader = imageLoader
    }

    //图片选择参数恢复默认，如果有自定义默认（saveAsDefault方法保存）优先恢复自定义默认
    @JvmStatic
    fun defaultConfig(): ImagePicker {
        pickHelper = customPickHelper?.copy() ?: PickHelper()
        return this
    }

    //当编辑过参数保存为自定义默认
    @JvmStatic
    fun saveAsDefault(): ImagePicker {
        customPickHelper = pickHelper
        return this
    }

    //清除缓存的已选择图片
    @JvmStatic
    fun clear() {
        pickHelper.selectedImages.clear()
        pickHelper.historyImages.clear()
    }

    //图片数量限制，默认9张
    @JvmStatic
    fun limit(max: Int): ImagePicker {
        pickHelper.limit = max
        return this
    }

    //是否显示相机，默认显示
    @JvmStatic
    fun showCamera(boolean: Boolean): ImagePicker {
        pickHelper.isShowCamera = boolean
        return this
    }

    //是否多选，默认显示
    @JvmStatic
    fun multiMode(boolean: Boolean): ImagePicker {
        pickHelper.isMultiMode = boolean
        return this
    }

    //是否裁剪
    @JvmStatic
    fun isCrop(boolean: Boolean): ImagePicker {
        pickHelper.isCrop = boolean
        return this
    }

    @JvmStatic
    fun CropConfig(focusStyle: CropImageView.Style, focusWidth: Int, focusHeight: Int, outPutX: Int, outPutY: Int, isSaveRectangle: Boolean) {
        pickHelper.focusStyle = focusStyle
        pickHelper.focusWidth = focusWidth
        pickHelper.focusHeight = focusHeight
        pickHelper.outPutX = outPutX
        pickHelper.outPutY = outPutY
        pickHelper.isSaveRectangle = isSaveRectangle
    }

    @JvmStatic
    fun pick(context: Context, listener: OnPickImageResultListener) {
        this.listener = listener
        ShadowActivity.start(context, 0, 0)
    }

    @JvmStatic
    fun camera(context: Context, listener: OnPickImageResultListener) {
        this.listener = listener
        ShadowActivity.start(context, 2,0)
    }

    @JvmStatic
    fun review(context: Context, position: Int, listener: OnPickImageResultListener) {
        this.listener = listener
        ShadowActivity.start(context, 1, position)
    }

    interface OnPickImageResultListener {
        fun onImageResult(imageItems: ArrayList<ImageItem>)
    }
}