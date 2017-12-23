package com.example.mystudy.tabLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mystudy.R;

import java.lang.reflect.Field;

/**
 * Created by Nature on 2017/12/13.
 */

public class SlidingTabLayout extends TabLayout {

    //每个tab的宽度
    private int tabWidth;

    //屏幕宽度
    private int mScreenWidth;

    //自定义指示器
    private Bitmap mSlideIcon;

    //滑动过程中指示器的水平偏移量
    private int mTranslationX;

    //指示器初始X偏移量
    private int mInitTranslationX;

    //指示器初始Y偏移量
    private int mInitTranslationY;

    //默认可见tab数量
    private static final int COUNT_DEFAULT_VISIBLE_TAB = 4;

    //可见tab数量，默认4个
    private int mTabVisibleCount = COUNT_DEFAULT_VISIBLE_TAB;

    //默认最后一个tab露出百分比
    private static final float RATIO_DEFAULT_LAST_VISIBLE_TAB = 0.55f;

    //最后一个tab露出百分比
    private float mLastTabVisibleRatio = RATIO_DEFAULT_LAST_VISIBLE_TAB;

    public SlidingTabLayout(Context context) {
        super(context);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSlideIcon = BitmapFactory.decodeResource(getResources(), R.drawable.home_jiaobiao);
        this.mScreenWidth = getResources().getDisplayMetrics().widthPixels;

        //异步修改Tab宽度
        post(new Runnable() {
            @Override
            public void run() {
                resetTabParams();
            }
        });
    }

    //重绘下标
    public void redrawIndicator(int position, float positionOffset) {
        mTranslationX = (int) ((position + positionOffset) * tabWidth);
        invalidate();
    }

    public void setmSlideIcon(Bitmap mSlideIcon) {
        this.mSlideIcon = mSlideIcon;
    }


    //绘制指示器

    @Nullable
    public LinearLayout getTabStrip() {
        Class<?> tabLayout = TabLayout.class;
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        llTab.setClipChildren(false);
        return llTab;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (mSlideIcon == null) {
            return;
        }
        canvas.save();
        //平移到正确位置，修正tabs的平移量
        canvas.translate(mInitTranslationX + mTranslationX, this.mInitTranslationY);
        canvas.drawBitmap(this.mSlideIcon, 0, 0, null);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    private void resetTabParams() {
        LinearLayout tabStrip = getTabStrip();
        if (tabStrip == null) {
            return;
        }
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            LinearLayout tabView = (LinearLayout) tabStrip.getChildAt(i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (mScreenWidth / (mTabVisibleCount + mLastTabVisibleRatio)), LinearLayout.LayoutParams.WRAP_CONTENT);
            tabView.setLayoutParams(params);
            //tab中的图标可以超过父容器
            tabView.setClipChildren(false);
            tabView.setClipToPadding(false);

            tabView.setPadding(0, 30, 0, 30);
        }
        initTranslationParams(tabStrip, mScreenWidth);
    }

    //初始化三角下标的坐标参数
    private void initTranslationParams(LinearLayout llTab, int screenWidth) {
        //图片指示器
        if (mSlideIcon == null) {
            return;
        }
        tabWidth = (int) (screenWidth / (mTabVisibleCount + mLastTabVisibleRatio));
        View firstView = llTab.getChildAt(0);
        if (firstView != null) {
            this.mInitTranslationX = (firstView.getLeft() + tabWidth / 2 - this.mSlideIcon.getWidth() / 2);
            this.mInitTranslationY = (getBottom() - getTop() - this.mSlideIcon.getHeight());
        }
    }
}
