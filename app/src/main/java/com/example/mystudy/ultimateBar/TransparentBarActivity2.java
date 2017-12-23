package com.example.mystudy.ultimateBar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.android.ultimatebar.UltimateBar;
import com.example.mystudy.R;

public class TransparentBarActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setTransparentBar(Color.GREEN, 100, Color.RED, 100);
        ((ImageView) findViewById(R.id.image_view)).setImageResource(R.drawable.yurisa_3);
    }
}
