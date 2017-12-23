package com.example.mystudy.imagePicker

import android.app.Activity
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.android.imagepicker.bean.ImageItem
import com.example.android.imagepicker.util.Utils
import com.example.mystudy.R
import java.io.File

/**
 * Created by Nature on 2017/12/12.
 */
class ImageAdapter constructor(
        private var images: List<ImageItem>
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){

    var listener: OnItemClickListener? = null

    fun updateData(images: List<ImageItem>) {
        this.images = images
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_image, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder?, position: Int) {
        holder?.imageView?.setOnClickListener({
            listener?.onItemClick(position)
        })
        Glide.with(holder?.imageView?.context)
                .load(Uri.fromFile(File(images[position].path)))
                .into(holder?.imageView)
    }

    override fun getItemCount(): Int {
        return images.size
    }


    inner class ImageViewHolder constructor(var rootView: View): RecyclerView.ViewHolder(rootView){
        val imageView: ImageView = rootView.findViewById(R.id.iv)
        init {
            rootView.layoutParams = AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.getImageItemWidth(rootView.context as Activity))
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}