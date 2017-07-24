package com.example.mystudy.cehuaDemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mystudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nature on 2017/7/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Integer> mlist;

    public RecyclerAdapter(Context context, List<Integer> list) {
        mlist = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_cehua, null, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txt.setText("第" + (mlist.get(position)+1) + "项");
        holder.layout.scrollTo(0, 0);
    }

    @Override
    public int getItemCount() {
        if (mlist != null) {
            return mlist.size();
        } else {
            return 0;
        }
    }

    public void removeRecycle(int position) {
        mlist.remove(position);
        notifyDataSetChanged();
        if (mlist.size() == 0) {
            Toast.makeText(context, "已经没有数据了", Toast.LENGTH_SHORT).show();
        }
    }
}
