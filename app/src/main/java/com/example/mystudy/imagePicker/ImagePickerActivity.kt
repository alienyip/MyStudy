package com.example.mystudy.imagePicker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.android.imagepicker.ImagePicker
import com.example.android.imagepicker.bean.ImageItem
import com.example.android.imagepicker.util.Utils
import com.example.android.imagepicker.view.GridSpacingItemDecoration
import com.example.mystudy.R
import kotlinx.android.synthetic.main.activity_image_picker.*

class ImagePickerActivity : AppCompatActivity(), ImagePicker.OnPickImageResultListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)
        //使用自定义默认参数或者默认参数,并清除Application启动之后选择图片缓存
        ImagePicker.defaultConfig()
        //默认不裁剪
        cb_crop.setOnCheckedChangeListener({_, isChecked -> ImagePicker.isCrop(isChecked) })
        cb_multi.isChecked = true//默认多选
        cb_multi.setOnCheckedChangeListener({_, isChecked -> ImagePicker.multiMode(isChecked)})
        btn_pick.setOnClickListener{
            //选择图片，第二次进入会自动带入之前选择的图片（未重置图片参数）
            ImagePicker.pick(this@ImagePickerActivity, this@ImagePickerActivity)
        }
        btn_camera.setOnClickListener {
            //直接打开相机
            ImagePicker.camera(this@ImagePickerActivity, this@ImagePickerActivity)
        }
        recycler_view.layoutManager = GridLayoutManager(this, 3)
        val imageAdapter = ImageAdapter(ArrayList())
        imageAdapter.listener = object : ImageAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                //回顾已选择图片，可以删除
                ImagePicker.review(this@ImagePickerActivity, position, this@ImagePickerActivity)
            }
        }
        recycler_view.addItemDecoration(GridSpacingItemDecoration(3, Utils.dp2px(this, 2f), false))
        recycler_view.adapter = imageAdapter
    }

    override fun onImageResult(imageItems: ArrayList<ImageItem>) {
        (recycler_view.adapter as ImageAdapter).updateData(imageItems)
    }

    override fun onDestroy() {
        super.onDestroy()
        ImagePicker.clear()
    }
}
