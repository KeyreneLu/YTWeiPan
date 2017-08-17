package com.jdyy.ytwp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 登录界面
 * Created by Administrator on 2016/8/15 0015.
 */
public class HelloActivity extends BaseActivity {

    @Bind(R.id.hello_activity)
    LinearLayout mHelloActivity;

    private Animation animation;
    private View view;
    private SharedPreferences sharedPreferences;
    static String token;
    private int allRecorders = 0;// 全部记录数
    boolean isFirst = false;
    String inputCode;
    /**
     * 两个切换的动画
     */
    private Animation mFadeIn;
    private Animation mFadeInScale;
    private Animation mFadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hello);
        ButterKnife.bind(this);
        inputCode = (String) SPUtils.get(HelloActivity.this, "inputCode", "");
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mFadeIn.setDuration(1000);
        mFadeInScale = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in_scale);
        mFadeInScale.setDuration(1000);

        mHelloActivity.startAnimation(mFadeIn);
        mFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mHelloActivity.startAnimation(mFadeInScale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mFadeInScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation.setFillAfter(true);
                isFirst = (boolean) SPUtils.get(HelloActivity.this, "isFirst", false);
                if (!isFirst) {
                    goToNextActivity(GuideActivity.class);
                } else if (!inputCode.isEmpty()){
                    goToNextActivity(DeblockActivity.class);
                }else {
                    goToNextActivity(MainActivity.class);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }
}