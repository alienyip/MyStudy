package com.example.mystudy.ncalendardemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mystudy.R;

public class NCalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncalendar);
    }

    public void month(View view) {
        startActivity(new Intent(this, MonthCalendarActivity.class));
    }

    public void week(View view) {
        startActivity(new Intent(this, WeekCalendarActivity.class));
    }

    public void monthAndWeek(View view) {
        startActivity(new Intent(this, MonthAndWeekCalendarActivity.class));
    }
}
