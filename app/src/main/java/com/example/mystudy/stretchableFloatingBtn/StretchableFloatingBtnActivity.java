package com.example.mystudy.stretchableFloatingBtn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mystudy.R;
import com.example.stretchablefloatingbutton.FlodableButton;



public class StretchableFloatingBtnActivity extends AppCompatActivity {

    private FlodableButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretchable_floating_btn);
        fb = (FlodableButton) findViewById(R.id.fb);

        fb.setOnClickListener(new FlodableButton.OnClickListener() {
            @Override
            public void onClick(FlodableButton sfb) {
                fb.startScroll();
            }
        });

        fb.setFoldListener(new FlodableButton.FoldListener() {
            @Override
            public void onFold(boolean isIncrease, FlodableButton sfb) {
                String text = isIncrease? "展开了" : "折叠了";
                Toast.makeText(StretchableFloatingBtnActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
