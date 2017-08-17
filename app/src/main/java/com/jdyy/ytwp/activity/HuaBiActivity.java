package com.jdyy.ytwp.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.adapter.SilverAdapter;
import com.jdyy.ytwp.bean.Silver;
import com.jdyy.ytwp.views.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 我的花币界面
 * Created by Administrator on 2016/8/27 0027.
 */
public class HuaBiActivity extends BaseActivity implements XListView.IXListViewListener, View.OnClickListener {


    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.iv_home_left)
    ImageView mIvHomeLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.Xlv_record)
    XListView mXlvRecord;
    @Bind(R.id.coin_sum)
    TextView mCoinSum;
    @Bind(R.id.btn_bug)
    Button mBtnBug;

    Handler mHandler;
    List<Silver> silvers = new ArrayList<>();
    private SilverAdapter mAdapter;
    private int xListStatus = 0;
    private int start = 0;
    private LayoutInflater mInflater;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huabi);
        ButterKnife.bind(this);
        initView();
        setlistener();
    }

    private void initView() {
        setToolbar1();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.dl_bg);
        }
        Resources resource = getBaseContext().getResources();
        ColorStateList csl = resource.getColorStateList(R.color.colorPrimary);
        if (csl != null) {
            mTitle.setTextColor(csl);
        }
        mRlHead.setBackgroundResource(R.color.dl_bg);

        mRlLeft.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.wdhb);
        mIvHomeLeft.setVisibility(View.VISIBLE);

        silvers = getsilvers();
        mAdapter = new SilverAdapter(HuaBiActivity.this, silvers,"0");
        mXlvRecord.setAdapter(mAdapter);
        mXlvRecord.setPullLoadEnable(true);
        mXlvRecord.setXListViewListener(this);
        mHandler = new Handler();
    }

    public List<Silver> getsilvers() {
        List<Silver> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int sum = (int) (Math.random() * 100);
            int State = (int) (Math.random() * 2);
            Silver testsilvers = new Silver("09:28", "周六", String.valueOf(sum), String.valueOf(State), "奔跑吧银币");
            list.add(testsilvers);
        }
        return list;
    }

    @Override
    public void onRefresh() {
        xListStatus = 1;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = ++xListStatus;
                silvers.clear();
                silvers.addAll(getsilvers());
                mAdapter = new SilverAdapter(HuaBiActivity.this, silvers,"0");
                mXlvRecord.setAdapter(mAdapter);
                onLoad();

            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                silvers.addAll(getsilvers());
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 1000);
    }

    private void onLoad() {
        mXlvRecord.stopRefresh();
        mXlvRecord.stopLoadMore();
    }


    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mBtnBug.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.btn_bug:
                goToActivity(SilverActivity.class);
                break;
        }
    }
}
