package com.example.mystudy.cehuaDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.mystudy.R;

import java.util.ArrayList;
import java.util.List;

public class CehuaActivity extends AppCompatActivity {

    private List<Integer> lists = new ArrayList<>();
    private MyRecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cehua);
        initData();
        recyclerView = (MyRecyclerView) findViewById(R.id.my_recylcer);
        adapter = new RecyclerAdapter(this, lists);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }



    private void initData() {
        for (int i = 0; i < 20; i++) {
            lists.add(i);
        }
    }
}
