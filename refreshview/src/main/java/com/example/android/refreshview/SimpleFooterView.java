package com.example.android.refreshview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Nature on 2017/11/15.
 */

public class SimpleFooterView extends BaseFooterView{

    private TextView mText;

    private ProgressBar progressBar;

    private View view;

    private CustomRefreshView customRefreshView;

    public SimpleFooterView(@NonNull Context context) {
        this(context, null);
    }

    public SimpleFooterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleFooterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view = LayoutInflater.from(getContext()).inflate(R.layout.footer_view, this);
        progressBar = (ProgressBar) view.findViewById(R.id.footer_view_progressbar);
        mText = (TextView) view.findViewById(R.id.footer_view_tv);
    }

    @Override
    public void onLoadingMore() {
        progressBar.setVisibility(VISIBLE);
        mText.setVisibility(VISIBLE);
        mText.setText("疯狂加载中。。。");
        view.setOnClickListener(null);
    }

    public void showText() {
        progressBar.setVisibility(GONE);
        mText.setVisibility(VISIBLE);
    }

    @Override
    public void onNoMore() {
        showText();
        mText.setText("-- 没有更多了 --");
        view.setOnClickListener(null);
    }

    @Override
    public void onError() {
        showText();
        mText.setText("--  出错了，点我重试  --");
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //变更footerView ui，重新执行加载
                onLoadingMore();
                customRefreshView.mListener.onLoadMore();
            }
        });
    }

    @Override
    public void setCustomRefreshView(CustomRefreshView customRefreshView) {
        this.customRefreshView = customRefreshView;
    }
}
