package com.example.android.imagepicker.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.android.imagepicker.ImagePicker
import com.example.android.imagepicker.R
import com.example.android.imagepicker.bean.ImageItem

/**
 * Created by Nature on 2017/12/4.
 */
class SmallPreviewAdapter(
        private val mActivity: Activity,
        var images: List<ImageItem> = ArrayList()
) : RecyclerView.Adapter<SmallPreviewAdapter.SmallPreviewViewHolder>(){

    var current: ImageItem? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SmallPreviewViewHolder {
        return SmallPreviewViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_small_preview, parent, false))
    }

    override fun onBindViewHolder(holder: SmallPreviewViewHolder?, position: Int) {
        holder?.bind(position)
    }

    override fun getItemCount(): Int = images.size

    override fun getItemId(position: Int): Long = position.toLong()

    inner class SmallPreviewViewHolder(private var mView: View) : RecyclerView.ViewHolder(mView){

        val iv_small = mView.findViewById<ImageView>(R.id.iv_small)
        val v_frame = mView.findViewById<View>(R.id.v_frame)

        fun bind(position: Int) {
            mView.setOnClickListener {
                listener?.onItemClick(position, images[position])
            }
            if (TextUtils.equals(current?.path, images[position].path)) {
                v_frame.visibility = View.VISIBLE
            } else {
                v_frame.visibility = View.GONE
            }
            ImagePicker.imageLoader.displayImage(mActivity, images[position].path!!, iv_small, iv_small.width, iv_small.height)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, imageItem: ImageItem)
    }
}