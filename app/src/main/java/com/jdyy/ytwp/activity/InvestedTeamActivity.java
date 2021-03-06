package com.jdyy.ytwp.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 投资小队界面
 * Created by Administrator on 2016/8/30 0030.
 */
public class InvestedTeamActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.tv_rebate)
    TextView mTvRebate;
    @Bind(R.id.tv_register_number)
    TextView mTvRegisterNumber;
    @Bind(R.id.tv_real_number)
    TextView mTvRealNumber;
    @Bind(R.id.tv_invested_number)
    TextView mTvInvestedNumber;
    @Bind(R.id.iv_contact_detail)
    ImageView mIvContactDetail;
    @Bind(R.id.tv_rebate2)
    TextView mTvRebate2;
    @Bind(R.id.tv_register_number2)
    TextView mTvRegisterNumber2;
    @Bind(R.id.tv_real_number2)
    TextView mTvRealNumber2;
    @Bind(R.id.tv_invested_number2)
    TextView mTvInvestedNumber2;
    @Bind(R.id.iv_contact_detail2)
    ImageView mIvContactDetail2;
    @Bind(R.id.tv_rebate3)
    TextView mTvRebate3;
    @Bind(R.id.tv_register_number3)
    TextView mTvRegisterNumber3;
    @Bind(R.id.tv_real_number3)
    TextView mTvRealNumber3;
    @Bind(R.id.tv_invested_number3)
    TextView mTvInvestedNumber3;
    @Bind(R.id.ban)
    TextView mBan;
    @Bind(R.id.btn_security)
    Button mBtnSecurity;

    private LayoutInflater mInflater;
    View mRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investedteam);
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
        mTitle.setText(R.string.tzxd);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }


    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mIvContactDetail.setOnClickListener(this);
        mIvContactDetail2.setOnClickListener(this);
        mBan.setOnClickListener(this);
        mBtnSecurity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.iv_contact_detail:
                goToActivity(TeamCrewActivity.class);
                break;
            case R.id.iv_contact_detail2:
                goToActivity(TeamDetailActivity.class);
                break;
            case R.id.ban:
                ShowBanDialog();
                break;
            case R.id.btn_security:
                Intent intent = new Intent(InvestedTeamActivity.this,SecurityActivity.class);
                intent.putExtra("flag","0");
                startActivity(intent);
                break;
        }
    }

    private void ShowBanDialog() {
        mInflater = LayoutInflater.from(InvestedTeamActivity.this);
        mRootView = mInflater.inflate(R.layout.layout_dialog_ban, null);
        //必须要加style，不然会有白色头部
        final Dialog mDialog = new Dialog(InvestedTeamActivity.this, R.style.Dialog);
        mDialog.getWindow().setContentView(mRootView);
        mDialog.show();
    }
}
