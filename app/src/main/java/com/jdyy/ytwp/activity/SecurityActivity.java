package com.jdyy.ytwp.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
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
 * 保证金明细及收益明细界面
 * Created by Administrator on 2016/8/30 0030.
 */
public class SecurityActivity extends BaseActivity implements XListView.IXListViewListener {
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
    @Bind(R.id.xlv_security)
    XListView mXlvSecurity;
    @Bind(R.id.tv_security)
    TextView mTvSecurity;
    @Bind(R.id.tv_security_number)
    TextView mTvSecurityNumber;

    private SilverAdapter mAdapter;
    List<Silver> data = new ArrayList<>();
    Handler mHandler;
    private int xListStatus = 0;
    private int start = 0;
    private LayoutInflater mInflater;

    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        flag = getIntent().getStringExtra("flag");
        ButterKnife.bind(this);
        initView();
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
        if (flag.equals("0")) {
            mTitle.setText(R.string.bzjmx);
            mTvSecurity.setText(R.string.wdbzj);
        } else if (flag.equals("1")) {
            mTitle.setText(R.string.symx);
            mTvSecurity.setText(R.string.wdsy);
        }
        mIvHomeLeft.setVisibility(View.VISIBLE);

        data = getsilvers();
        mAdapter = new SilverAdapter(SecurityActivity.this, data, "1");
        mXlvSecurity.setAdapter(mAdapter);
        mXlvSecurity.setPullLoadEnable(true);
        mXlvSecurity.setXListViewListener(this);
        mHandler = new Handler();

        mIvHomeLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public List<Silver> getsilvers() {
        List<Silver> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int sum = (int) (Math.random() * 100);
            int State = (int) (Math.random() * 2);
            Silver testsilvers = new Silver("09:28", "周六", String.valueOf(sum), String.valueOf(State), "15272305435");
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
                data.clear();
                data.addAll(getsilvers());
                mAdapter = new SilverAdapter(SecurityActivity.this, data, "1");
                mXlvSecurity.setAdapter(mAdapter);
                onLoad();

            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                data.addAll(getsilvers());
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 1000);
    }

    private void onLoad() {
        mXlvSecurity.stopRefresh();
        mXlvSecurity.stopLoadMore();
    }
}
