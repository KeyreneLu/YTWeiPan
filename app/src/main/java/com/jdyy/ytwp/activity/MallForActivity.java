package com.jdyy.ytwp.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.bean.Cash;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 商城兑换界面
 * Created by Administrator on 2016/8/29 0029.
 */
public class MallForActivity extends BaseActivity implements View.OnClickListener {

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
    @Bind(R.id.cash_money)
    TextView mCashMoney;
    @Bind(R.id.tv_lose)
    TextView mTvLose;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.tv_silver)
    TextView mTvSilver;
    @Bind(R.id.tv_deadline)
    TextView mTvDeadline;
    @Bind(R.id.rl_cash)
    RelativeLayout mRlCash;
    @Bind(R.id.iv_reduce)
    ImageView mIvReduce;
    @Bind(R.id.tv_bug_number)
    TextView mTvBugNumber;
    @Bind(R.id.iv_add)
    ImageView mIvAdd;
    @Bind(R.id.tv_silver_number)
    TextView mTvSilverNumber;
    @Bind(R.id.tv_existing)
    TextView mTvExisting;
    @Bind(R.id.mf_choose)
    ImageButton mMfChoose;
    @Bind(R.id.mf_xieyi)
    TextView mMfXieyi;
    @Bind(R.id.tv_product_state)
    TextView mTvProductState;
    @Bind(R.id.tv_use_way)
    TextView mTvUseWay;
    @Bind(R.id.tv_earning)
    TextView mTvEarning;
    @Bind(R.id.btn_sure)
    Button mBtnSure;
    @Bind(R.id.mf_xy)
    LinearLayout mMfXy;
    @Bind(R.id.tv_recive)
    TextView mTvRecive;


    private String size;
    private String price;
    private String valid;
    Cash cash;
    private boolean ischosen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mallfor);
        ButterKnife.bind(this);
        cash = getIntent().getParcelableExtra("cash");
        size = cash.getsize();
        price = cash.getDate();
        valid = cash.getValid();
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
        mTitle.setText(R.string.scdh);
        mIvHomeLeft.setVisibility(View.VISIBLE);
        mCashMoney.setText(size);
        mTvSilver.setVisibility(View.VISIBLE);
        mTvLose.setText("兑换需");
        mTvTime.setText(price);
        mTvDeadline.setText(valid);
    }

    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
        mIvReduce.setOnClickListener(this);
        mMfXy.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
        mTvRecive.setOnClickListener(this);
        mMfChoose.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.iv_add:
                break;
            case R.id.iv_reduce:
                break;
            case R.id.tv_recive:
            case R.id.mf_choose:
                if (!ischosen) {
                    mMfChoose.setBackgroundResource(R.mipmap.icon_dg);
                    ischosen = true;
                } else {
                    mMfChoose.setBackgroundResource(R.mipmap.icon_dgh);
                    ischosen = false;
                }
                break;
            case R.id.btn_sure:
                break;
        }
    }
}
