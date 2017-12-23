package com.example.android.refreshview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Nature on 2017/11/15.
 */

public abstract class BaseFooterView extends FrameLayout implements FooterViewListener{
    public BaseFooterView(@NonNull Context context) {
        super(context);
    }

    public BaseFooterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFooterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setCustomRefreshView(CustomRefreshView customRefreshView);
}
