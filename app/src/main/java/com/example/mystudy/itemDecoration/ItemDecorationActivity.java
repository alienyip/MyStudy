package com.example.mystudy.itemDecoration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mystudy.R;

public class ItemDecorationActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnDivider, mBtnDivider1, mBtnDivider2, mBtnDivider3, mBtnDivider4, mBtnDivider5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_decoration);

        mBtnDivider = (Button) findViewById(R.id.btn_divider);
        mBtnDivider1 = (Button) findViewById(R.id.btn_divider1);
        mBtnDivider2 = (Button) findViewById(R.id.btn_divider2);
        mBtnDivider3 = (Button) findViewById(R.id.btn_divider3);
        mBtnDivider4 = (Button) findViewById(R.id.btn_divider4);
        mBtnDivider5 = (Button) findViewById(R.id.btn_divider5);
        mBtnDivider.setOnClickListener(this);
        mBtnDivider1.setOnClickListener(this);
        mBtnDivider2.setOnClickListener(this);
        mBtnDivider3.setOnClickListener(this);
        mBtnDivider4.setOnClickListener(this);
        mBtnDivider5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_divider:
                Intent intent = new Intent(ItemDecorationActivity.this, DividerActivity.class);
                startActivity(intent);

                break;
            case R.id.btn_divider1:
                Intent intent1 = new Intent(ItemDecorationActivity.this, ColorDividerActivity.class);
                startActivity(intent1);

                break;
            case R.id.btn_divider2:
                Intent intent2 = new Intent(ItemDecorationActivity.this, TimelineActivity.class);
                startActivity(intent2);

                break;
            case R.id.btn_divider3:
                Intent intent3 = new Intent(ItemDecorationActivity.this, BookRankActivity.class);
                startActivity(intent3);

                break;
            case R.id.btn_divider4:
                Intent intent4 = new Intent(ItemDecorationActivity.this, HeaderActivity.class);
                startActivity(intent4);

                break;
            case R.id.btn_divider5:
                Intent intent5 = new Intent(ItemDecorationActivity.this, StickyHeaderActivity.class);
                startActivity(intent5);

                break;

            default:
                break;
        }
    }
}
