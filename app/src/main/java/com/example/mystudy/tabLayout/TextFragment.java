package com.example.mystudy.tabLayout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mystudy.R;

/**
 * Created by Nature on 2017/12/13.
 */

public class TextFragment extends Fragment {

    private View rootView;
    private TextView tvContent;
    private String textContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment1, container, false);
        tvContent = (TextView) rootView.findViewById(R.id.tv_content);
        tvContent.setText(textContent);
        return rootView;
    }

    public void setText(String txt) {
        this.textContent = txt;
    }
}
