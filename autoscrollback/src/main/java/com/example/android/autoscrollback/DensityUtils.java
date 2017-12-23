package com.example.android.autoscrollback;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Nature on 2017/11/9.
 */

public class DensityUtils {

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
