package com.example.android.imagepicker.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.imagepicker.C
import com.example.android.imagepicker.ImagePicker
import com.example.android.imagepicker.bean.ImageItem

class ShadowActivity : AppCompatActivity() {

    private var type: Int = 0
    private var position: Int = 0

    companion object {
        fun start(context: Context, type: Int, position: Int) {
            val intent = Intent(context, ShadowActivity::class.java)
            intent.putExtra(C.EXTRA_TYPE, type)
            intent.putExtra(C.EXTRA_POSITION, position)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = intent.extras[C.EXTRA_TYPE] as Int
        position = intent.extras[C.EXTRA_POSITION] as Int
        startPick()
    }

    private fun startPick() {
        when (type) {
            0 -> ImageGridActivity.startForResult(this, 101, false)
            1 -> ImagePreviewDelActivity.startForResult(this, 102, position)
            2 -> ImageGridActivity.startForResult(this, 101,true)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val historyImages = ImagePicker.pickHelper.historyImages
        if (resultCode == Activity.RESULT_OK && data != null) {
            val images = data.extras[C.EXTRA_IMAGE_ITEMS] as ArrayList<ImageItem>
            historyImages.let {
                it.clear()
                it.addAll(images)
            }
            ImagePicker.listener?.onImageResult(images)
        } else if (resultCode == Activity.RESULT_CANCELED) {
            ImagePicker.pickHelper.selectedImages.let {
                it.clear()
                it.addAll(historyImages)
            }
            ImagePicker.listener?.onImageResult(historyImages)
        }
        ImagePicker.listener = null
        finish()
    }
}
