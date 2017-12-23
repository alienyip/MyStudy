package com.example.mystudy.autoScrollBack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mystudy.MainActivity;
import com.example.mystudy.R;

public class AutoScrollBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_scroll_back);
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewDemoActivity.start(AutoScrollBackActivity.this);
            }
        });
        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GridViewDemoActivity.start(AutoScrollBackActivity.this);
            }
        });
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewDemoActivity.start(AutoScrollBackActivity.this);
            }
        });
    }
}
