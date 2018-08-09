package com.example.zjl.myrecyclerview.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.example.zjl.myrecyclerview.R;
import com.example.zjl.myrecyclerview.adapter.CalculatorAdapter;
import com.example.zjl.myrecyclerview.app.AppApplication;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/7/27 0027.
 */

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CalculatorAdapter calculatorAdapter;
    List<String> num = Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.Ca_num));
    List<String> type = Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.Ca_type));


    TextView popAnim;
    TextView recycler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.rcy);
        popAnim = findViewById(R.id.popAnim);
        recycler = findViewById(R.id.recycler);

        initAdapter();
        initAnim();
    }

    private void initAnim() {
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.pop);
        popAnim.setAnimation(scaleAnimation);

        Animation scaleAnimation2 = AnimationUtils.loadAnimation(this, R.anim.recycler);
        recycler.setAnimation(scaleAnimation2);
    }

    private void initAdapter() {
        calculatorAdapter = new CalculatorAdapter(this, num);
        recyclerView.setAdapter(calculatorAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));


        //第一种动画 recyclerView布局中添加 android:layoutAnimation="@anim/layout_animation_slide_in_bottom"
        //第二种动画 (https://blog.csdn.net/ss1168805219/article/details/77990828)
        LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_slide_in_bottom);
        recyclerView.setLayoutAnimation(controller);
    }
}
