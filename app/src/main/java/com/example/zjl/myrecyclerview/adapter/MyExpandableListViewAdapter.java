package com.example.zjl.myrecyclerview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zjl.myrecyclerview.bean.ChildItem;
import com.example.zjl.myrecyclerview.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public class MyExpandableListViewAdapter extends BaseExpandableListAdapter{
    private Context mContext;
    private LayoutInflater mInflater = null;
    private List<String> mGroupItems = null;
    private List<List<ChildItem>> mData = null;

    private class GroupViewHolder {
        TextView mGroupName;
        TextView mGroupCount;

    }

    private class ChildViewHolder {
        ImageView mIcon;
        TextView mChildName;
        TextView mDetail;
    }

    public MyExpandableListViewAdapter(Context context, List<String> groupItems,List<List<ChildItem>> childItems){
        this.mContext = context;
        this.mData = childItems;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mGroupItems = groupItems;
    }

    @Override
    public ChildItem getChild(int groupPosition, int childPosition) {
        return mData.get(groupPosition).get(childPosition);
    }

//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return mData.get(groupPosition).get(childPosition);
//    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Log.e("childData",groupPosition+"  "+childPosition+"  "+mData.size());
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_expand_child, null);
        }
        ChildViewHolder holder = new ChildViewHolder();
        holder.mIcon = (ImageView) convertView.findViewById(R.id.img);
        holder.mChildName = (TextView) convertView.findViewById(R.id.item_name);
        holder.mDetail = (TextView) convertView.findViewById(R.id.item_details);
        holder.mIcon.setBackgroundResource(getChild(groupPosition,childPosition).getResId());
        holder.mChildName.setText(getChild(groupPosition, childPosition).getName());
        holder.mDetail.setText(getChild(groupPosition, childPosition).getDetail());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mData.size() <= groupPosition) {
            return 0;
        }//防止点击没有数据的group造成数组越界
        return mData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupItems.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_expand_group, null);
        }
        GroupViewHolder holder = new GroupViewHolder();
        holder.mGroupName = (TextView) convertView.findViewById(R.id.group_name);
        holder.mGroupCount = (TextView) convertView.findViewById(R.id.group_count);
        holder.mGroupName.setText(mGroupItems.get(groupPosition));
        if (mData.size()<=groupPosition||mData.get(groupPosition).isEmpty()) {
            holder.mGroupCount.setText("0");
        }else {
            holder.mGroupCount.setText(String.valueOf(getChildrenCount(groupPosition)));
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
