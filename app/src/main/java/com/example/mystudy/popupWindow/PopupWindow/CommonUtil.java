package com.example.mystudy.popupWindow.PopupWindow;

import android.content.Context;
import android.view.View;

/**
 * Created by Nature on 2017/6/29.
 */

public class CommonUtil {

    public static void measureWidthAndHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }

    public static float dp2px(Context  context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}
