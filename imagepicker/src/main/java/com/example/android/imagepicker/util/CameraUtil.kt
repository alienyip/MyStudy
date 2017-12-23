package com.example.android.imagepicker.util

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Nature on 2017/11/29.
 */
object CameraUtil {
    fun takePicture(activity: Activity, requestCode: Int): File {
        var takeImageFile =
                if (Utils.existSDCard())
                    File(Environment.getExternalStorageDirectory(), "/DCIM/camera/")
                else
                    Environment.getDataDirectory()
        takeImageFile = createFile(takeImageFile, "IMG_", ".jpg")
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
            val uri: Uri
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                uri = Uri.fromFile(takeImageFile)
            } else {
                // 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
                // 并且这样可以解决MIUI系统上拍照返回size为0的情况
                uri = FileProvider.getUriForFile(activity, ProviderUtil.getFileProviderName(activity), takeImageFile)
                //加入uri权限 要不三星手机不能拍照
                val resInfoList = activity.packageManager.queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY)
                resInfoList
                        .map { it.activityInfo.packageName }
                        .forEach { activity.grantUriPermission(it, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION) }
            }
            Log.e("nanchen", ProviderUtil.getFileProviderName(activity))
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        }
        activity.startActivityForResult(takePictureIntent, requestCode)
        return takeImageFile
    }

    fun createFile(folder: File, prefix: String, suffix: String): File {
        if (!folder.exists() || !folder.isDirectory) folder.mkdir()
        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA)
        val filename = prefix + dateFormat.format(Date(System.currentTimeMillis())) + suffix
        return File(folder, filename)
    }
}