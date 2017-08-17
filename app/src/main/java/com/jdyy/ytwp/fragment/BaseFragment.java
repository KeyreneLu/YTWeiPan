package com.jdyy.ytwp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;


    //获取fragment布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected Activity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        return view;
    }

    /**
     * 跳转另一个Activity
     *
     * @param cls 目标Activity
     */
    public void goToActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        getHoldingActivity().startActivity(intent);
    }

    /**
     * 跳转另一个Activity
     *
     * @param cls 目标Activity
     */
    public void goToNextActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        getHoldingActivity().startActivity(intent);
        getHoldingActivity().finish();
    }
}
