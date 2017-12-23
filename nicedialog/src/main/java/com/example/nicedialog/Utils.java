package com.example.nicedialog;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Nature on 2017/10/20.
 */

public class Utils {
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
}
