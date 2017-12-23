package com.example.mystudy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nature on 2017/6/27.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Demo> demoList;

    private Context mContext;

    public MyAdapter(List<Demo> list) {
        demoList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView demotv;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            demotv = (TextView) itemView.findViewById(R.id.demotv);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.demo_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Demo demo = demoList.get(position);
        holder.demotv.setText(demo.getDemoname());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(demo.getDemoname(),demo.getDemoURL());
                Intent intent = new Intent(mContext, demo.getDemoactivity());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return demoList.size();
    }
}
