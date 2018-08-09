package com.example.zjl.myrecyclerview.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.zjl.myrecyclerview.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Administrator on 2018/8/8 0008.
 */

public class SurfaceViewActivity extends Activity {

    ImageView scanLine;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.act_surfaceview);

        initView();
        init();
    }

    private void initView() {
        scanLine = findViewById(R.id.Iv_screen_img);
    }

    private void init() {
//        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT,
//                0.9f);
//        animation.setDuration(4000);
//        animation.setRepeatCount(-1);
//        animation.setRepeatMode(Animation.RESTART);
//        scanLine.startAnimation(animation);

        startAnimationSet();
    }

    ObjectAnimator objectAnimator=null;
    public void startAnimationSet() {
        ViewTreeObserver vto = scanLine.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scanLine.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int height = scanLine.getHeight();
                objectAnimator = ObjectAnimator.ofFloat(scanLine, "translationY", -height, 0);
                objectAnimator.setDuration(1500);
                objectAnimator.setRepeatCount(-1);
                objectAnimator.setRepeatMode(ValueAnimator.RESTART);
                objectAnimator.start();
            }
        });
        scanLine.setAlpha(0.45f);

        System.out.print("");
    }
}
