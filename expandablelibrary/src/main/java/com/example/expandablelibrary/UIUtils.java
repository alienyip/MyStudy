package com.example.expandablelibrary;

import android.content.Context;

/**
 * Created by Nature on 2017/7/18.
 */

public class UIUtils {

    //将sp值转换为px值，保证文字大小不变
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
