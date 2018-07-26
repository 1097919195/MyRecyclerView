package com.example.zjl.myrecyclerview.activity;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.zjl.myrecyclerview.R;
import com.example.zjl.myrecyclerview.adapter.MyExpandableListViewAdapter;
import com.example.zjl.myrecyclerview.bean.ChildItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    MyExpandableListViewAdapter adapter;
    List<List<ChildItem>> itemList = new ArrayList<>();
    List<ChildItem> items = new ArrayList<>();
    List<ChildItem> items1 = new ArrayList<>();
    List<ChildItem> items2 = new ArrayList<>();
    ChildItem item = new ChildItem();
    ChildItem item1 = new ChildItem();
    ChildItem item2 = new ChildItem();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        expandableListView = findViewById(R.id.expand_list);

        List<String> groupItems = Arrays.asList(this.getResources().getStringArray(R.array.group));
        item.setName("德玛");
        item.setDetail("asdfsdfasdfasdf");
        item1.setName("德玛xiya");
        item1.setDetail("asdfsdfaasdfasdfsdfasdf");
        items.add(item);
        items.add(item1);
        items1.add(item1);
        items2.add(item2);
        itemList.add(items);
        itemList.add(items1);
        itemList.add(items2);
        adapter = new MyExpandableListViewAdapter(this,groupItems,itemList);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this,String.valueOf(groupPosition)+"  "+String.valueOf(childPosition),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        if (getIsArtInUse()) {
            Log.e("TAG", "当前是ART虚拟机");
        }else {
            Log.e("TAG", "当前是Dalvik虚拟机");
        }
    }

    private boolean getIsArtInUse() {
        final String vmVersion = System.getProperty("java.vm.version");
        return vmVersion != null && vmVersion.startsWith("2");
    }
}
