package com.example.zjl.myrecyclerview.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.zjl.myrecyclerview.R;
import com.example.zjl.myrecyclerview.adapter.MyExpandableListViewAdapter;
import com.example.zjl.myrecyclerview.bean.ChildItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        adapter = new MyExpandableListViewAdapter(this, groupItems, itemList);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this, String.valueOf(groupPosition) + "  " + String.valueOf(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        if (getIsArtInUse()) {
            Log.e("TAG", "当前是ART虚拟机");
        } else {
            Log.e("TAG", "当前是Dalvik虚拟机");
        }

        initListener();

        initFlie();
    }

    private void initFlie() {
        try {
            //写(没有文件会自动创建)
            FileOutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "logTest.txt");
            String s = "hahahahahaha";
            try {
                out.write(s.getBytes());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //读
            FileInputStream in = new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "logTest.txt");
            int length;
            byte[] bytes = new byte[1024];
            try {
                while ((length = in.read(bytes)) != -1) {
                    Log.e("asdf Int", String.valueOf(length));
                    Log.e("asdf Read", new String(bytes, 0, length));//将数据变为字符串输出
                }
            } catch (IOException e) {
                Log.e("asdf2", e.getMessage());
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            Log.e("asdf1", e.getMessage());
            e.printStackTrace();
        }
    }

    private void initListener() {
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_lock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LockActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.surface).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SurfaceViewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.animBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnimActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.treeListAct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TreeListActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.clipAct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClipActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.listview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewSectionActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean getIsArtInUse() {
        final String vmVersion = System.getProperty("java.vm.version");
        return vmVersion != null && vmVersion.startsWith("2");
    }
}
