package com.example.mystudy.ultimateBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.ultimatebar.UltimateBar;
import com.example.mystudy.R;

public class HideBarActivity1 extends AppCompatActivity {

    private View toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        toolbar = findViewById(R.id.include_toolbar);
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            UltimateBar ultimateBar = new UltimateBar(this);
            ultimateBar.setHideBar(true);
        }
    }
}
