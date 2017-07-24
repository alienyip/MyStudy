package com.example.mystudy.itemDecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mystudy.R;

import java.util.ArrayList;
import java.util.List;

public class HeaderActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<String> data;
    TestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

        mRecyclerView = (RecyclerView) findViewById(R.id.header_recyclerview);

        initDatas();

        mAdapter = new TestAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        SectionDecoration.GroupInfoCallback callback = new SectionDecoration.GroupInfoCallback() {
            @Override
            public GroupInfo getGroupInfo(int position) {
                int groupId = position / 5;
                int index = position % 5;
                GroupInfo groupInfo = new GroupInfo(groupId, groupId+"");
                groupInfo.setPosition(index);
                return groupInfo;
            }
        };
        mRecyclerView.addItemDecoration(new SectionDecoration(this,callback));
    }

    private void initDatas() {
        data = new ArrayList<>();
        for (int i = 0; i < 56; i++) {
            data.add(i + " test ");
        }
    }
}
