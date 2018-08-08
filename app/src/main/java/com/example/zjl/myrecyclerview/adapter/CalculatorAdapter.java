package com.example.zjl.myrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.zjl.myrecyclerview.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/15 0015.
 */

public class CalculatorAdapter extends RecyclerView.Adapter<CalculatorAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> data;
    private OnItemClickListener onItemClickListener;
    private List<String> type;

    public CalculatorAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        this.data = data;
    }

    public CalculatorAdapter(Context mContext, List<String> data , List<String> type) {
        this.mContext = mContext;
        this.data = data;
        this.type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_calculator_num, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String str = data.get(position);
        holder.text.setText(str);
        //fixme type 没有的话这里需要修改
//        String typeStr = type.get(position);
//
//        holder.text.setOnClickListener(v -> {
//            if (onItemClickListener != null) {
//                onItemClickListener.onClick(position,typeStr);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public MyViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.num);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position, String type);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
