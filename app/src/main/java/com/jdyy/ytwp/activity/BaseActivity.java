package com.jdyy.ytwp.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    List<BaseActivity> mActivities = new LinkedList<BaseActivity>();
    private List<BaseActivity> copyLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivities.add(this);
        setToolbar1();
    }

    void setToolbar1() {
        setToolbar(0xff4400);
    }

    /**
     * 设置状态栏的颜色
     *
     * @param color 颜色
     */
    protected void setToolbar(int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(color);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 跳转到下一个Activity，结束当前Activity
     * @param cls 要去的Activity
     */
    public void goToNextActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转到下一个Activity
     * @param cls 要去的Activity
     */
    public void goToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出当前activity
     */
    @Override
    protected void onDestroy() {
        synchronized (mActivities) {
            mActivities.remove(this);
        }
        super.onDestroy();
    }

    /**
     * 退出所有打开的activity
     */
    public void killAll() {
        synchronized (mActivities) {
            copyLists = new LinkedList<BaseActivity>(mActivities);
        }
        for (BaseActivity Activity : copyLists) {
            Activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}