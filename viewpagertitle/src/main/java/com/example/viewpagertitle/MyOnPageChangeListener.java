package com.example.viewpagertitle;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;
import static com.example.viewpagertitle.Tool.getScreenWidth;
import static com.example.viewpagertitle.Tool.getTextViewLength;

/**
 * Created by Nature on 2017/10/22.
 */

public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

    private final Context context;
    private ArrayList<TextView> textViews;
    private int pagerCount;
    private ViewPager pager;
    private ViewPagerTitle viewPagerTitle;
    private int lastPosition;
    private DynamicLine dynamicLine;
    private int everyLength;
    private int dis;
    private int fixLeftDis;
    private int lineWidth;
    private static final String TAG = "test_tag";
    private int[] location = new int[2];
    private int screenWidth;

    public MyOnPageChangeListener(Context context , ViewPager viewPager, DynamicLine dynamicLine, ViewPagerTitle viewPagerTitle, int allLength, int margin, int fixLeftDis) {
        this.viewPagerTitle = viewPagerTitle;
        this.pager = viewPager;
        this.dynamicLine = dynamicLine;
        this.context = context;
        textViews = viewPagerTitle.getTextView();
        pagerCount = textViews.size();
        screenWidth = getScreenWidth(context);
        lineWidth = (int) getTextViewLength(textViews.get(0));
        everyLength = allLength / pagerCount;
        dis = margin;
        this.fixLeftDis = fixLeftDis;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (lastPosition > position) {
            dynamicLine.updateView((position + positionOffset) * everyLength + dis + fixLeftDis, (lastPosition + 1) * everyLength - dis);
        } else {
            if (positionOffset > 0.5f) {
                positionOffset = 0.5f;
            }
            dynamicLine.updateView(lastPosition * everyLength + dis + fixLeftDis, (position + positionOffset * 2) * everyLength + dis + lineWidth);
        }
    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, lastPosition + "[[[[");
        viewPagerTitle.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        boolean scrollRight;//页面向右
        if (state == SCROLL_STATE_SETTLING) {
            scrollRight = lastPosition < pager.getCurrentItem();
            lastPosition = pager.getCurrentItem();
            if (lastPosition + 1 < textViews.size() && lastPosition - 1 >=0) {
                textViews.get(scrollRight ? lastPosition + 1 : lastPosition - 1).getLocationOnScreen(location);
                if (location[0] > screenWidth) {
                    viewPagerTitle.smoothScrollBy(screenWidth / 2, 0);
                } else if (location[0] < 0) {
                    viewPagerTitle.smoothScrollBy(-screenWidth / 2, 0);
                }
            }
        }
    }
}
