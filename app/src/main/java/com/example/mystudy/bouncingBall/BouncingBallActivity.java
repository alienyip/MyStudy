package com.example.mystudy.bouncingBall;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.mystudy.R;
import com.example.mystudy.bouncingBall.view.BouncingBallConfig;
import com.example.mystudy.bouncingBall.view.BouncingBallView;

public class BouncingBallActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private BouncingBallView bouncingBallView1;
    private BouncingBallView bouncingBallView2;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouncing_ball);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        bouncingBallView1 = (BouncingBallView) findViewById(R.id.view);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bouncingBallView1.letUsAnimate();
            }
        });
        BouncingBallConfig config = new BouncingBallConfig();
        config = new BouncingBallConfig.Builder()
                .setBallColor(Color.BLUE)
                .setBallCount(6)
                .setBallRadius(30)
                .create();
        bouncingBallView2 = new BouncingBallView(this);
        bouncingBallView2.setLayoutParams(new LinearLayout.LayoutParams(600, 600));
        bouncingBallView2.init(config);
        linearLayout.addView(bouncingBallView2);
        bouncingBallView2.letUsAnimate();
    }
}
