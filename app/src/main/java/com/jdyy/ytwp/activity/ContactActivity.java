package com.jdyy.ytwp.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的人脉界面
 * Created by Administrator on 2016/8/30 0030.
 */
public class ContactActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.ban)
    TextView mBan;
    @Bind(R.id.btn_upgrade_team)
    Button mBtnUpgradeTeam;
    @Bind(R.id.declare3)
    TextView mDeclare3;

    private LayoutInflater mInflater;
    View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
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
        mTitle.setText(R.string.wdrm);
        mIvHomeLeft.setVisibility(View.VISIBLE);

        //改变10%的颜色
        SpannableStringBuilder str = new SpannableStringBuilder(mDeclare3.getText().toString());
        str.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dl_bg)),
                str.length() - 11, str.length() - 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mDeclare3.setText(str);
    }


    private void setlistener() {
        mBan.setOnClickListener(this);
        mBtnUpgradeTeam.setOnClickListener(this);
        mIvContactDetail.setOnClickListener(this);
        mIvHomeLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ban:
                ShowBanDialog();
                break;
            case R.id.btn_upgrade_team:
                ShowUpgradeDialog();
                break;
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.iv_contact_detail:
                goToActivity(TeamDetailActivity.class);
                break;
        }
    }

    private void ShowBanDialog() {
        mInflater = LayoutInflater.from(ContactActivity.this);
        mRootView = mInflater.inflate(R.layout.layout_dialog_ban, null);
        //必须要加style，不然会有白色头部
        final Dialog mDialog = new Dialog(ContactActivity.this, R.style.Dialog);
        mDialog.getWindow().setContentView(mRootView);
        mDialog.show();
    }

    private void ShowUpgradeDialog() {
        mInflater = LayoutInflater.from(ContactActivity.this);
        mRootView = mInflater.inflate(R.layout.layout_dialog_service, null);
        Button mBtnCall = (Button) mRootView.findViewById(R.id.btn_call);
        //必须要加style，不然会有白色头部
        final Dialog mDialog = new Dialog(ContactActivity.this, R.style.Dialog);
        mDialog.getWindow().setContentView(mRootView);
        mDialog.show();

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = mDialog.getWindow().getAttributes(); // 获取对话框当前的参数值
        p.width = 400;
        p.height = 420;
        // 高度设置为屏幕的0.5
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        dialogWindow.setAttributes(p);

        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                String string_phoneNum = getResources().getString(R.string.phone);//得到电话号码
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                //url:统一资源定位符
                //uri:统一资源标示符（更广）
                intent.setData(Uri.parse("tel:" + string_phoneNum));
                //开启系统拨号器
                ContactActivity.this.startActivity(intent);
            }
        });
    }
}
