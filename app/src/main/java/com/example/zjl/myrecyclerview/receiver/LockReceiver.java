package com.example.zjl.myrecyclerview.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2018/8/6 0006.
 */

public class LockReceiver extends DeviceAdminReceiver {
    /**
     * 接受其他组件发送来的广播
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        System.out.println("onreceiver");
    }
    @Override
    public void onEnabled(Context context, Intent intent) {
        System.out.println("激活使用");
        super.onEnabled(context, intent);
    }
    @Override
    public void onDisabled(Context context, Intent intent) {
        System.out.println("取消激活");
        super.onDisabled(context, intent);
    }
}
