package com.example.mystudy.itemDecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mystudy.R;

import java.util.ArrayList;
import java.util.List;

public class ColorDividerActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    TestAdapter mAdapter;
    List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divider);

        mRecyclerView = (RecyclerView) findViewById(R.id.divider_recyclerview);
        initDatas();
        mAdapter = new TestAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutmanager);
        mRecyclerView.addItemDecoration(new ColorDividerItemDecoration());
    }

    private void initDatas() {
        data = new ArrayList<>();
        for (int i = 0; i < 56; i++) {
            data.add(i + " test ");
        }
    }
}
