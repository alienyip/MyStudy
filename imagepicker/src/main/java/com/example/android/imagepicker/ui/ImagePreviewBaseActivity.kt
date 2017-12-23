package com.example.android.imagepicker.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.imagepicker.ImagePicker
import com.example.android.imagepicker.PickHelper
import com.example.android.imagepicker.R
import com.example.android.imagepicker.adapter.ImagePageAdapter
import kotlinx.android.synthetic.main.activity_image_preview.*
import kotlinx.android.synthetic.main.include_top_bar.*
import uk.co.senab.photoview.PhotoViewAttacher

/**
 * Created by Nature on 2017/12/4.
 */
abstract class ImagePreviewBaseActivity : AppCompatActivity(), PhotoViewAttacher.OnPhotoTapListener{

    protected lateinit var imagePageAdapter : ImagePageAdapter
    protected var pickHelper: PickHelper = ImagePicker.pickHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)
        initView()
    }

    private fun initView() {
        btn_back.setOnClickListener { finish() }

        imagePageAdapter = ImagePageAdapter(this)
        imagePageAdapter.listener = this
        viewpager.adapter = imagePageAdapter
    }
}
