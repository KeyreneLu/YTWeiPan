package com.jdyy.ytwp.fragment;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.activity.HuaBiActivity;
import com.jdyy.ytwp.activity.InvestedTeamActivity;
import com.jdyy.ytwp.activity.SilverMallActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class FindFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.yb_sum)
    TextView mYbSum;
    @Bind(R.id.rl_yb)
    RelativeLayout mRlYb;
    @Bind(R.id.sc_sum)
    TextView mScSum;
    @Bind(R.id.rl_sc)
    RelativeLayout mRlSc;
    @Bind(R.id.tz_sum)
    TextView mTzSum;
    @Bind(R.id.tl_tz)
    RelativeLayout mTlTz;
    @Bind(R.id.ll_lucky)
    LinearLayout mLlLucky;
    @Bind(R.id.ll_coin)
    LinearLayout mLlCoin;
    @Bind(R.id.tv_invite)
    TextView mTvInvite;
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initView();
        setlistener();
        return rootView;
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.dl_bg);
        }
        Resources resource = getActivity().getBaseContext().getResources();
        ColorStateList csl = resource.getColorStateList(R.color.zc_bg);
        if (csl != null) {
            mTitle.setTextColor(csl);
        }
        mRlHead.setBackgroundResource(R.color.dl_bg);
        mTitle.setText(R.string.find);
    }

    private void setlistener() {
        mLlCoin.setOnClickListener(this);
        mLlLucky.setOnClickListener(this);
        mRlYb.setOnClickListener(this);
        mRlSc.setOnClickListener(this);
        mTlTz.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_coin:

                break;

            case R.id.ll_lucky:

                break;
            case R.id.rl_yb:
                goToActivity(HuaBiActivity.class);
                break;
            case R.id.rl_sc:
                goToActivity(SilverMallActivity.class);
                break;
            case R.id.tl_tz:
                goToActivity(InvestedTeamActivity.class);
                break;
        }
    }
}
