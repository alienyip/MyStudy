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
 * Created by Nature on 2017/7/11.
 */

public class SectionDecoration extends RecyclerView.ItemDecoration {

    private GroupInfoCallback mCallback;
    private int mHeaderHeight;
    private int mDividerHeight;
    private Paint mPaint;

    private float mTextSize;
    private Paint.FontMetrics mFontMetrics;

    private float mTextOffsetX;
    private Paint mTextPaint;

    public SectionDecoration(Context context,GroupInfoCallback callback) {
        this.mCallback = callback;
        mDividerHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_divider_height);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_height);
        mTextSize = context.getResources().getDimensionPixelOffset(R.dimen.header_textsize);

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

        int position = parent.getChildAdapterPosition(view);

        if (mCallback != null) {
            GroupInfo groupInfo = mCallback.getGroupInfo(position);

            //如果是组内的第一个则将间距撑开为一个Header的高度，或者就是普通的分割线高度
            if (groupInfo != null && groupInfo.isFirstViewInGroup()) {
                outRect.top = mHeaderHeight;
            } else {
                outRect.top = mDividerHeight;
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);

            int index = parent.getChildAdapterPosition(view);

            if (mCallback != null) {
                GroupInfo groupInfo = mCallback.getGroupInfo(index);
                //只有组内的第一个ItemView之上才绘制
                if (groupInfo.isFirstViewInGroup()) {
                    int left = parent.getPaddingLeft();
                    int top = view.getTop() - mHeaderHeight;
                    int right = parent.getWidth() - parent.getPaddingRight();
                    int bottom = view.getTop();
                    //绘制Header
                    c.drawRect(left,top,right,bottom,mPaint);

                    float titleX = left + mTextOffsetX;
                    float titleY = bottom - mFontMetrics.descent;
                    c.drawText(groupInfo.getTitle(), titleX, titleY, mTextPaint);
                }

            }
        }
    }



    public interface GroupInfoCallback {
        GroupInfo getGroupInfo(int position);
    }

}
