package com.jdyy.ytwp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jdyy.ytwp.Gesture.GestureContentView;
import com.jdyy.ytwp.Gesture.GestureDrawline;
import com.jdyy.ytwp.R;
import com.jdyy.ytwp.utils.SPUtils;
import com.jdyy.ytwp.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/21 0021.
 */

public class GestureActivity extends BaseActivity{
    @Bind(R.id.gesture_container)
    RelativeLayout mGestureContainer;
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    private String flag;
    private GestureContentView mGestureContentView;
    private String mParamSetUpcode = null;
    private String mParamPhoneNumber;
    private boolean mIsFirstInput = true;
    private String mFirstPassword = null;
    private String mConfirmPassword = null;
    private int mParamIntentCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        ButterKnife.bind(this);
        flag = getIntent().getStringExtra("flag");
        initView();
    }

    private void initView() {


        mGestureContentView = new GestureContentView(this, false, "", new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {
                if (!isInputPassValidate(inputCode)) {
                    T.showShort(GestureActivity.this, "最少链接4个点, 请重新输入");
                    mGestureContentView.clearDrawlineState(0L);
                    return;
                }
                if (mIsFirstInput) {
                    mFirstPassword = inputCode;
                    mGestureContentView.clearDrawlineState(0L);
                    T.showShort(GestureActivity.this, "请重新再绘制一次");
                } else {
                    if (inputCode.equals(mFirstPassword)) {
                        Toast.makeText(GestureActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        SPUtils.put(GestureActivity.this,"inputCode",inputCode);
                        SPUtils.put(GestureActivity.this,"isOpen",true);
                        mGestureContentView.clearDrawlineState(0L);
                        if (flag.equals("0")){
                            goToNextActivity(MainActivity.class);
                        }else {
                            GestureActivity.this.finish();
                        }
                    } else {
                        T.showShort(GestureActivity.this, "与上一次绘制不一致，请重新绘制");
                        // 左右移动动画
//                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureActivity.this, R.anim.shake);
//                        mTextTip.startAnimation(shakeAnimation);
                        // 保持绘制的线，1.5秒后清除
                        mGestureContentView.clearDrawlineState(1300L);
                    }
                }
                mIsFirstInput = false;
            }

            @Override
            public void checkSuccess() {

            }

            @Override
            public void checkFail() {

            }
        });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }


    private boolean isInputPassValidate(String inputPassword) {
        if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
            return false;
        }
        return true;
    }
}
