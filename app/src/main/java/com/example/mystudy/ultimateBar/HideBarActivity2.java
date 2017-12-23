package com.example.mystudy.ultimateBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.android.ultimatebar.UltimateBar;
import com.example.mystudy.R;

public class HideBarActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ((ImageView) findViewById(R.id.image_view)).setImageResource(R.drawable.yurisa_2);

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
