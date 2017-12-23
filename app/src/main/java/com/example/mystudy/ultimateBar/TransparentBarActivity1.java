package com.example.mystudy.ultimateBar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.ultimatebar.UltimateBar;
import com.example.mystudy.R;

public class TransparentBarActivity1 extends AppCompatActivity {
    private View toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setTransparentBar(Color.BLUE, 100, Color.GREEN, 100);
        toolbar = findViewById(R.id.include_toolbar);
        toolbar.setVisibility(View.GONE);
    }
}
