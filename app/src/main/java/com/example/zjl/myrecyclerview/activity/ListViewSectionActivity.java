package com.example.zjl.myrecyclerview.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zjl.myrecyclerview.R;
import com.example.zjl.myrecyclerview.adapter.GroupListAdapter;
import com.example.zjl.myrecyclerview.bean.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/6 0006.
 */

public class ListViewSectionActivity extends Activity {
    private Context mContext;
    private ListView lv_list;

    private List<DataModel> list=null;
    private List<DataModel> groupkey=new ArrayList<DataModel>();
    private List<DataModel> aList = new ArrayList<DataModel>();
    private List<DataModel> bList = new ArrayList<DataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_section);

        mContext = this;
        lv_list = (ListView) findViewById(R.id.lv_list);
        initData();
    }

    private void initData(){
        list = new ArrayList<DataModel>();

        DataModel model = new DataModel();
        model.setTitle("------1------");
        groupkey.add(model);
        model = new DataModel();
        model.setTitle("------2------");
        groupkey.add(model);

        for(int i=0; i<8; i++){
            model = new DataModel();
            model.setContent("上海");
            model.setStartTime("20170505"+i);
            model.setEndTime("20170505"+(i+10));
            aList.add(model);
        }
        //add是将传入的参数作为当前List中的一个Item存储，即使你传入一个List也只会另当前的List增加1个元素
        //addAll是传入一个List，将此List中的所有元素加入到当前List中，也就是当前List会增加的元素个数为传入的List的大小
        list.add(groupkey.get(0));
        list.addAll(aList);

        for(int i=0; i<25; i++){
            model = new DataModel();
            model.setStartTime("20170905"+i);
            model.setEndTime("20170905"+(i+10));
            model.setContent("科技");
            bList.add(model);
        }
        list.add(groupkey.get(1));
        list.addAll(bList);

        lv_list.setAdapter(new GroupListAdapter(mContext, groupkey, list));
    }
}
