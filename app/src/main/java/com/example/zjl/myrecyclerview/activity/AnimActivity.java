package com.example.zjl.myrecyclerview.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zjl.myrecyclerview.R;

/**
 * Created by Administrator on 2018/8/9 0009.
 */

public class AnimActivity extends Activity {
    private ImageView iv_animation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim);

        iv_animation = (ImageView) findViewById(R.id.iv_animation);
        iv_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnimActivity.this, "点击了图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 补间(视图)动画
     *
     * @param v
     */
    public void testTweenAnimation(View v) {
        TranslateAnimation animation = new TranslateAnimation(0, iv_animation.getWidth(), 0, iv_animation.getHeight());
        animation.setDuration(3000);
        animation.setFillAfter(true);
        iv_animation.startAnimation(animation);
    }


    private AnimatorSet animatorSet;

    /**
     * 测试属性动画
     */
    public void testPropertyAnimation(View v) {
//      x轴上移动
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv_animation, "translationX", 0, iv_animation.getWidth());
//      y轴上移动
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(iv_animation, "translationY", 0, iv_animation.getHeight());
        AnimatorSet set = new AnimatorSet();
//      两个动画一起播放
        set.playTogether(animator3, animator4);
//      播放时间2秒
        set.setDuration(2000);
//      开始播放
        set.start();

//      //另外一种写法
//        iv_animation.animate()
//                 .translationXBy(iv_animation.getWidth())
//                 .translationYBy(iv_animation.getWidth())
//                 .setDuration(2000)
//                 .setInterpolator(new BounceInterpolator())
//                 .start();


//        ObjectAnimator animator = ObjectAnimator.ofFloat(iv_animation, "translationX", 0,iv_animation.getWidth());
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv_animation, "translationY", 0,iv_animation.getHeight());
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setDuration(2000);
//        animatorSet.setInterpolator(new BounceInterpolator());
//        //两个动画一起播放
//        animatorSet.playTogether(animator, animator2);
//        //开始播放
//        animatorSet.start();

    }

    public void reset(View v) {
        iv_animation.clearAnimation();

    }
}
