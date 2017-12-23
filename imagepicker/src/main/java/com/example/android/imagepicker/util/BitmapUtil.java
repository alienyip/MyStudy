package com.example.android.imagepicker.util;

import android.media.ExifInterface;

import java.io.IOException;

/**
 * Created by Nature on 2017/12/11.
 */

public class BitmapUtil {

    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片， 并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
}
