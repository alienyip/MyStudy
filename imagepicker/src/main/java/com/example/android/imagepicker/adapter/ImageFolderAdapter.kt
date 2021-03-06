package com.example.android.imagepicker.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.android.imagepicker.ImagePicker
import com.example.android.imagepicker.R
import com.example.android.imagepicker.bean.ImageFolder
import com.example.android.imagepicker.util.Utils

/**
 * Created by Nature on 2017/11/30.
 */
class ImageFolderAdapter(private val mActivity: Activity, folders: MutableList<ImageFolder>?) : BaseAdapter(){
    private val mInflater: LayoutInflater
    private val mImageSize: Int
    private var imageFolders: MutableList<ImageFolder>? = null
    var selectIndex = 0
        set(i) {
            if (selectIndex != i) {
                field = i
                notifyDataSetChanged()
            }
        }

    init {
        imageFolders = if (folders != null && folders.size > 0) folders else ArrayList()
        mImageSize = Utils.getImageItemWidth(mActivity)
        mInflater = mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun refreshData(folders: MutableList<ImageFolder>?) {
        if (folders != null && folders.size > 0) imageFolders = folders
        else imageFolders?.clear()
        notifyDataSetChanged()
    }

    override fun getCount(): Int = imageFolders?.size ?:0

    override fun getItem(position: Int): ImageFolder = imageFolders!![position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder: ViewHolder
        if (view == null) {
            view = mInflater.inflate(R.layout.adapter_folder_list_item, parent, false)
            holder = ViewHolder(view)
        } else {
            holder = view.tag as ViewHolder
        }

        val (name, _, cover, images) = getItem(position)
        holder.folderName.text = name
        holder.imageCount.text = mActivity.getString(R.string.ip_folder_image_count, images.size)
        if (cover?.path != null) {
            ImagePicker.imageLoader?.displayImage(mActivity, cover.path!!, holder.cover, mImageSize, mImageSize)
        }
        if (selectIndex == position) {
            holder.folderCheck.visibility = View.VISIBLE
        } else {
            holder.folderCheck.visibility = View.INVISIBLE
        }

        return view!!
    }


    private inner class ViewHolder(view: View){
        internal var cover: ImageView = view.findViewById(R.id.iv_cover)
        internal var folderName: TextView = view.findViewById(R.id.tv_folder_name)
        internal var imageCount: TextView = view.findViewById(R.id.tv_image_count)
        internal var folderCheck: ImageView = view.findViewById(R.id.iv_folder_check)

        init {
            view.tag = this
        }
    }
}