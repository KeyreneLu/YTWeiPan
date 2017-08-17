package com.jdyy.ytwp.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.views.XListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 钱包界面
 * Created by Administrator on 2016/8/19 0019.
 */
public class WalletActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.balance)
    TextView mBalance;
    @Bind(R.id.tv_use)
    TextView mTvUse;
    @Bind(R.id.tv_occupy)
    TextView mTvOccupy;
    @Bind(R.id.rv_ticket)
    XListView mRvTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ButterKnife.bind(this);
        initView();
        setlistener();
    }

    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
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
        mTitle.setText(R.string.ye);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home_left:
                finish();
                break;
        }
    }
}
