package com.example.zjl.myrecyclerview.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Administrator on 2018/7/31 0031.
 */

public class AppApplication extends Application {
    private static AppApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }

    public static Context getAppContext() {
        return baseApplication;
    }
    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
}
