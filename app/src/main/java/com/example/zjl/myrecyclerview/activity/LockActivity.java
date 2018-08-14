package com.example.zjl.myrecyclerview.activity;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.zjl.myrecyclerview.R;
import com.example.zjl.myrecyclerview.receiver.LockReceiver;

/**
 * Created by Administrator on 2018/8/6 0006.
 */


//https://www.jianshu.com/p/8934d47aed3b  设备管理器操错
public class LockActivity extends Activity {
    ComponentName mAdminName;
    DevicePolicyManager mDPM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lock);
        mAdminName = new ComponentName(this, LockReceiver.class);
        mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        //如果设备管理器尚未激活，这里会启动一个激活设备管理器的Intent,具体的表现就是第一次打开程序时，手机会弹出激活设备管理器的提示，激活即可。
        if (!mDPM.isAdminActive(mAdminName)) {
            showAdminManagement(mAdminName);
        }

        lockScreen(findViewById(R.id.btn));

        initListener();
    }

    private void initListener() {
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDataWithVideoPlayerProvider();
            }
        });
    }

    // https://www.jianshu.com/p/601086916c8f
    private void addDataWithVideoPlayerProvider() {
        Uri bookUri = Uri.parse("content://com.zjl.contentproviderdemo.BookProvider/book");
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookName", "叫什么名字好呢");
        getContentResolver().insert(bookUri, contentValues);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "bookName"}, null, null, null);
        if (bookCursor != null) {
            while (bookCursor.moveToNext()) {
                Log.e("provider", "ID:" + bookCursor.getInt(bookCursor.getColumnIndex("_id"))
                        + "  BookName:" + bookCursor.getString(bookCursor.getColumnIndex("bookName")));
            }
            bookCursor.close();
        }

        Uri userUri = Uri.parse("content://com.zjl.contentproviderdemo.BookProvider/user");
        contentValues.clear();
        contentValues.put("userName", "叶叶叶");
        contentValues.put("sex", "男");
        getContentResolver().insert(userUri, contentValues);
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "userName", "sex"}, null, null, null);
        if (userCursor != null) {
            while (userCursor.moveToNext()) {
                Log.e("provider", "ID:" + userCursor.getInt(userCursor.getColumnIndex("_id"))
                        + "  UserName:" + userCursor.getString(userCursor.getColumnIndex("userName"))
                        + "  Sex:" + userCursor.getString(userCursor.getColumnIndex("sex")));
            }
            userCursor.close();
        }
    }

    //执行锁屏  http://gudong.name/2015/05/26/lock_screen.html
    public void lockScreen(View view){
        if (mDPM.isAdminActive(mAdminName)) {
            mDPM.lockNow();
        }
    }

    //激活设备管理器
    private void showAdminManagement(ComponentName mAdminName) {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "activity device");
        startActivityForResult(intent,1);
    }
}
