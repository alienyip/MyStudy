package com.example.expandablelibrary;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Nature on 2017/7/18.
 */

public class ExpandableLinearLayout extends LinearLayout implements View.OnClickListener {
    private boolean useDefaultBottom;
    private int arrowResId;
    private int textColor;
    private float fontSize;
    private String hideText;
    private String expandText;
    private int defaultItemCount;
    private View bottomView;
    private ImageView ivArrow;
    private TextView tvTip;
    private boolean isExpand = false;

    private OnStateChangeListener mListener;
    private static final String TAG = ExpandableLinearLayout.class.getSimpleName();
    private boolean hasBottom;

    public ExpandableLinearLayout(Context context) {
        this(context, null);
    }

    public ExpandableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableLinearLayout);
        defaultItemCount = typedArray.getInt(R.styleable.ExpandableLinearLayout_defaultItemCount, 2);
        expandText = typedArray.getString(R.styleable.ExpandableLinearLayout_expandText);
        hideText = typedArray.getString(R.styleable.ExpandableLinearLayout_hideText);
        fontSize = typedArray.getDimension(R.styleable.ExpandableLinearLayout_tipTextSize, UIUtils.sp2px(context, 14));
        textColor = typedArray.getColor(R.styleable.ExpandableLinearLayout_tipTextColor, Color.parseColor("#666666"));
        arrowResId = typedArray.getResourceId(R.styleable.ExpandableLinearLayout_arrowDownImg, R.mipmap.arrow_down);
        useDefaultBottom = typedArray.getBoolean(R.styleable.ExpandableLinearLayout_useDefaultBottom, true);
        typedArray.recycle();

        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        findViews();
    }

    private void findViews() {
        bottomView = View.inflate(getContext(), R.layout.item_ell_bottom, null);
        ivArrow = (ImageView) bottomView.findViewById(R.id.iv_arrow);
        ivArrow.setImageResource(arrowResId);
        tvTip = (TextView) bottomView.findViewById(R.id.tv_tip);
        tvTip.setTextColor(textColor);
        tvTip.getPaint().setTextSize(fontSize);
        bottomView.setOnClickListener(this);
    }

    public void addItem(View view) {
        int childCount = getChildCount();
        if (!useDefaultBottom) {
            //如果不适用默认底部
            addView(view);
            if (childCount > defaultItemCount) {
                hide();
            }
            return;
        }

        //使用默认底部
        if (!hasBottom) {
            //如果还没有底部
            addView(view);
        } else {
            addView(view, childCount - 2);
        }
        refreshUI(view);
    }

    private void refreshUI(View view) {
        int childCount = getChildCount();
        if (childCount > defaultItemCount) {
            if (childCount - defaultItemCount == 1) {
                //刚超过默认，判断是否要添加底部
                justToAddBottom(childCount);
            }
            view.setVisibility(GONE);
        }
    }

    @Override
    public void setOrientation(int orientation) {
        if (LinearLayout.HORIZONTAL == orientation) {
            throw new IllegalArgumentException("ExpandableTextView only supports Vertical Orientation.");
        }
        super.setOrientation(orientation);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        Log.i(TAG, "childCount: " + childCount);
        justToAddBottom(childCount);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void justToAddBottom(int childCount) {
        if (childCount > defaultItemCount) {
            if (useDefaultBottom && !hasBottom) {
                //要使用默认底部，并且还没有底部
                addView(bottomView);
                hide();
                hasBottom = true;
            }
        }
    }


    @Override
    public void onClick(View v) {
        toggle();
    }

    public void toggle() {
        if (isExpand) {
            hide();
            tvTip.setText(expandText);
        } else {
            expand();
            tvTip.setText(hideText);
        }
        doArrowAnim();
        isExpand = !isExpand;

        //回调
        if (mListener != null) {
            mListener.onStateChanged(isExpand);
        }
    }

    private void doArrowAnim() {
        if (isExpand) {
            //当前是展开，将执行收起，箭头由上变为下
            ObjectAnimator.ofFloat(ivArrow, "rotation", -180, 0).start();
        } else {
            //当前是收起，将执行展开，箭头由下变为上
            ObjectAnimator.ofFloat(ivArrow, "rotation", 0, 180).start();
        }
    }

    //展开
    private void expand() {
        for (int i = defaultItemCount; i < getChildCount(); i++) {
            //从默认显示条目位置以下的都显示出来
            View view = getChildAt(i);
            view.setVisibility(VISIBLE);
        }
    }

    //收起
    private void hide() {
        int endIndex = useDefaultBottom ? getChildCount() - 1: getChildCount();
        for (int i = defaultItemCount; i < endIndex; i++) {
            View view = getChildAt(i);
            view.setVisibility(GONE);
        }
    }


    public interface OnStateChangeListener {
        void onStateChanged(boolean isExpanded);
    }

    public void setOnStateChangeListener(OnStateChangeListener mListener) {
        this.mListener = mListener;
    }
}
