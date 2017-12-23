package com.example.mystudy.ultimateBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.android.ultimatebar.UltimateBar;
import com.example.mystudy.R;

public class ImmersionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar(true);

        ((ImageView) findViewById(R.id.image_view)).setImageResource(R.drawable.yurisa_1);
    }
}
