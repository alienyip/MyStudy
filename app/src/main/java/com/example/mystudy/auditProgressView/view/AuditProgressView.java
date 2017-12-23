package com.example.mystudy.auditProgressView.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.mystudy.R;

/**
 * Created by Nature on 2017/11/16.
 */

public class AuditProgressView extends View {
    // 标记该步骤是否完成
    private boolean mIsCurrentComplete;
    // 标记下一个步骤是否完成
    private boolean mIsNextComplete;
    //根据是否完成的标记 确定绘制的图片
    private Bitmap audit_drawBitmap;
    //自定义View的宽高
    private int width, height;
    private Paint paint;
    // 绘制文字
    private String text;
    // 有几个步骤
    private int stepCount;
    // 是否是第一步 第一步不需要画左边线
    private boolean mIsFirstStep;
    // 是否是最后一步 最后一步 不需要画右边线
    private boolean mIsLastStep;

    public AuditProgressView(Context context) {
        this(context, null);
    }

    public AuditProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AuditProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AuditProgressView, defStyleAttr, 0);
        mIsCurrentComplete = array.getBoolean(R.styleable.AuditProgressView_apv_isCurrentComplete, false);
        mIsNextComplete = array.getBoolean(R.styleable.AuditProgressView_apv_isNextComplete, false);
        mIsFirstStep = array.getBoolean(R.styleable.AuditProgressView_apv_isFirstStep, false);
        mIsLastStep = array.getBoolean(R.styleable.AuditProgressView_apv_isLastStep, false);
        stepCount = array.getInteger(R.styleable.AuditProgressView_apv_stepCount, 2);
        text = array.getString(R.styleable.AuditProgressView_apv_text);
        array.recycle();

        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 在宽高不是精确模式时，定义最小宽高

        if (widthMode != MeasureSpec.EXACTLY) {
            width = getContext().getResources().getDisplayMetrics().widthPixels / stepCount;
        }

        if (heightMode != MeasureSpec.EXACTLY) {
            height = dp2px(getContext(), 90);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 获取自定义View的宽高
        width = getWidth();
        height = getHeight();

        // 根据 当前步骤是否完成 确定中间的图片
        if (mIsCurrentComplete) {
            audit_drawBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audit_complete);
        } else {
            audit_drawBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audit_uncomplete);
        }

        //绘制图片
        canvas.drawBitmap(audit_drawBitmap, width / 2 - audit_drawBitmap.getWidth() / 2, height / 2 - audit_drawBitmap.getHeight() / 2, paint);


        // 根据当前步骤是否完成 确定绘制文字颜色
        String mString = text;
        TextPaint tp = new TextPaint();
        if (mIsCurrentComplete) {
            tp.setColor(Color.parseColor("#03A9F5"));
        } else {
            tp.setColor(Color.parseColor("#757575"));
        }

        // 绘制多行文字
        tp.setStyle(Paint.Style.FILL);
        Point point = new Point(width / 2, dp2px(getContext(), 70));
        tp.setTextSize(sp2px(getContext(), 14));
        textCenter(mString, tp, canvas, point, dp2px(getContext(), 57), Layout.Alignment.ALIGN_CENTER, 1, 0, false);

        // 绘制线条
        paint.setStrokeWidth(dp2px(getContext(), 2));

        if (!mIsFirstStep) {
            //绘制左边线
            // 根据当前步骤是否完成 来确定左边线条的颜色
            if (mIsCurrentComplete) {
                paint.setColor(Color.parseColor("#03A9F5"));
            } else {
                paint.setColor(Color.parseColor("#E4E4E4"));
            }
            canvas.drawLine(0, height / 2, width / 2 - audit_drawBitmap.getWidth() / 2 - dp2px(getContext(), 8), height / 2, paint);
        }

        // 根据是不是最后的步骤 确定是否有右边线条
        if (!mIsLastStep) {
            // 绘制右边线
            // 根据下一个步骤是否完成 来确定右边线条的颜色
            if (mIsNextComplete) {
                paint.setColor(Color.parseColor("#03A9F5"));
            } else {
                paint.setColor(Color.parseColor("#E4E4E4"));
            }
            canvas.drawLine(width / 2 + audit_drawBitmap.getWidth() +dp2px(getContext(), 8), height / 2, width, height / 2, paint);
        }
    }

    // 绘制多行文字
    private void textCenter(String string, TextPaint textPaint, Canvas canvas, Point point, int width, Layout.Alignment align, float spacingmult, float spacingadd, boolean includepad) {
        StaticLayout staticLayout = new StaticLayout(string, textPaint, width, align, spacingmult, spacingadd, includepad);
        canvas.save();
        canvas.translate(point.x - staticLayout.getWidth() / 2 , point.y - staticLayout.getHeight() / 2);
        staticLayout.draw(canvas);
        canvas.restore();
    }

    public void setIsCurrentComplete(boolean IsCurrentComplete) {
        this.mIsCurrentComplete = IsCurrentComplete;
    }

    public void setIsNextComplete(boolean IsNextComplete) {
        this.mIsNextComplete = IsNextComplete;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public void setIsFirstStep(boolean IsFirstStep) {
        this.mIsFirstStep = IsFirstStep;
    }

    public void setIsLastStep(boolean IsLastStep) {
        this.mIsLastStep = IsLastStep;
    }

    public int dp2px(Context context, float dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    public  int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
