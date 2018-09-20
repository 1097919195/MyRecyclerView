package com.example.zjl.myrecyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zjl.myrecyclerview.R;
import com.example.zjl.myrecyclerview.bean.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/20 0020.
 */

public class GroupListAdapter extends BaseAdapter {
    private Context mContext;
    private List<DataModel> list_group = new ArrayList<DataModel>();
    private List<DataModel> list_event = new ArrayList<DataModel>();

    public GroupListAdapter(Context context, List<DataModel> list_group, List<DataModel> list_event){
        mContext = context;
        this.list_group = list_group;
        this.list_event = list_event;
    }

    @Override
    public int getCount() {
        return list_event.size();
    }

    @Override
    public DataModel getItem(int position) {
        return list_event.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.items_section, null);
            viewHolder.ll_list_items = (LinearLayout) convertView.findViewById(R.id.ll_list_items);
            viewHolder.tv_start_time = (TextView) convertView.findViewById(R.id.tv_start_time);
            viewHolder.tv_end_time = (TextView) convertView.findViewById(R.id.tv_end_time);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.rl_group_items = (RelativeLayout) convertView.findViewById(R.id.rl_items_group);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (list_group.contains(getItem(position))){
            viewHolder.ll_list_items.setVisibility(View.GONE);
            viewHolder.rl_group_items.setVisibility(View.VISIBLE);
            viewHolder.tv_title.setText(getItem(position).getTitle());
        } else {
            viewHolder.ll_list_items.setVisibility(View.VISIBLE);
            viewHolder.rl_group_items.setVisibility(View.GONE);
            viewHolder.tv_start_time.setText(getItem(position).getStartTime());
            viewHolder.tv_end_time.setText(getItem(position).getEndTime());
            viewHolder.tv_content.setText(getItem(position).getContent());
        }
        return convertView;
    }

    //如果是只显示头部的item的话返回false,即不可以点击该item
    @Override
    public boolean isEnabled(int position) {
        if (list_group.contains(list_event.get(position))){
            return false;
        }
        return super.isEnabled(position);
    }


    class ViewHolder{
        LinearLayout ll_list_items;
        RelativeLayout rl_group_items;
        TextView tv_start_time, tv_end_time, tv_content, tv_title;
    }
}
