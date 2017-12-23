package com.example.mystudy.refreshView;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.refreshview.CustomRefreshView;
import com.example.mystudy.R;

import java.util.ArrayList;
import java.util.List;

public class RefreshViewActivity extends AppCompatActivity {

    private CustomRefreshView refreshView;
    private List<String> data;
    private RecyclerViewAdapter adapter;
    private int pagerSize = 10;
    private int mm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_view);

        refreshView = (CustomRefreshView) findViewById(R.id.refresh_view);
        data = new ArrayList<>();
        adapter = new RecyclerViewAdapter();
        refreshView.setAdapter(adapter);

        //模拟所有现象
        refreshView.setOnLoadListener(new CustomRefreshView.OnLoadLitener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        for (int i = 0; i < pagerSize; i++) {
                            if (mm >= 2) {
                                data.add(String.valueOf(i));
                            }
                        }
                        ++mm;
                        //模拟无数据界面
                        if (mm == 1) {
                            refreshView.setEmptyView("暂无数据");
                            refreshView.complete();
                            return;
                        }
                        //模拟网络出错界面
                        if (mm == 2) {
                            refreshView.setErrorView();
                            refreshView.complete();
                            return;
                        }

                        refreshView.complete();
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < pagerSize; i++) {
                            data.add(String.valueOf(i));
                        }
                        if (data.size() > 20 && data.size() < 50) {
                            refreshView.onError();
                        } else {
                            if (data.size() < 70) {
                                refreshView.stopLoadingMore();
                            }
                        }
                        if (data.size() >= 70) {
                            refreshView.onNoMore();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });
        refreshView.setRefreshing(true);
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RefreshViewActivity.this).inflate(R.layout.list_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, final int position) {
            holder.tv.setText("my position is" + position);
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RefreshViewActivity.this, "我是" + position + "号", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public ItemViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }


}
