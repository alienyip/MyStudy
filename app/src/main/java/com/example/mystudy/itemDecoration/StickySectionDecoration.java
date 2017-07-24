package com.example.mystudy.itemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.example.mystudy.R;

/**
 * Created by Nature on 2017/7/17.
 */

public class StickySectionDecoration extends RecyclerView.ItemDecoration {

    private  GroupInfoCallback mCallback;
    private int mHeaderHeight;
    private int mDividerHeight;
    private Paint mPaint;
    private float mTextOffsetX;
    private Paint.FontMetrics mFontMetrics;
    private TextPaint mTextPaint;
    private float mTextSize;

    public StickySectionDecoration(Context context, GroupInfoCallback callback) {
        this.mCallback = callback;
        mDividerHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_divider_height);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_height);
        mTextSize = context.getResources().getDimensionPixelOffset(R.dimen.header_textsize);

        mHeaderHeight = (int) Math.max(mHeaderHeight, mTextSize);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(mTextSize);
        mFontMetrics = mTextPaint.getFontMetrics();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int positon = parent.getChildAdapterPosition(view);

        if (mCallback != null) {
            GroupInfo groupInfo = mCallback.getGroupInfo(positon);

            //如果是组内的第一个则将间距撑开为一个Header的高度，或者就是普通的分割线高度
            if (groupInfo != null && groupInfo.isFirstViewInGroup()) {
                outRect.top = mHeaderHeight;
            } else {
                outRect.top = mDividerHeight;
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);

            if (mCallback != null) {
                GroupInfo groupInfo = mCallback.getGroupInfo(index);
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                if (i != 0) {
                    if (groupInfo.isFirstViewInGroup()) {
                        int top = view.getTop() - mHeaderHeight;
                        int bottom = view.getTop();
                        drawHeaderRect(c, groupInfo, left, top, right, bottom);
                    }
                } else {

                    //当 ItemView 是屏幕上第一个可见的View 时，不管它是不是组内第一个View
                    //它都需要绘制它对应的 StickyHeader。

                    // 还要判断当前的 ItemView 是不是它组内的最后一个 View

                    int top = parent.getPaddingTop();

                    if (groupInfo.isLastViewInGroup()) {
                        int suggestTop = view.getBottom() - mHeaderHeight;
                        if (suggestTop < top) {
                            top = suggestTop;
                        }
                    }

                    int bottom = top + mHeaderHeight;
                    drawHeaderRect(c, groupInfo, left, top, right, bottom);
                }
            }
        }
    }

    private void drawHeaderRect(Canvas c, GroupInfo groupInfo, int left, int top, int right, int bottom) {
        //绘制Header
        c.drawRect(left, top, right, bottom, mPaint);

        float titleX = left + mTextOffsetX;
        float titleY = bottom - mFontMetrics.descent;
        //绘制Title
        c.drawText(groupInfo.getTitle(),titleX,titleY,mTextPaint);
    }

    public GroupInfoCallback getCallback() {
        return mCallback;
    }

    public void setCallback(GroupInfoCallback callback) {
        this.mCallback = callback;
    }

    public interface GroupInfoCallback {
        GroupInfo getGroupInfo(int position);
    }
}
