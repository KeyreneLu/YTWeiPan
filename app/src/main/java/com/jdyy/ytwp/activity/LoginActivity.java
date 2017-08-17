package com.jdyy.ytwp.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 登录界面
 * Created by Administrator on 2016/8/24 0024.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.iv_home_left)
    ImageView mIvHomeLeft;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.et_zh)
    EditText mEtZh;
    @Bind(R.id.rl_zh)
    RelativeLayout mRlZh;
    @Bind(R.id.et_mm)
    EditText mEtMm;
    @Bind(R.id.iv_clear)
    ImageView mIvClear;
    @Bind(R.id.saw)
    ImageView mSaw;
    @Bind(R.id.dl_btn)
    Button mDlBtn;
    @Bind(R.id.tv_zhuce)
    TextView mTvZhuce;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_change_password)
    TextView mTvChangePassword;

    private boolean IsShow = false;
    private boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        isOpen = (boolean) SPUtils.get(this, "isOpen", false);
        initView();
        setlistener();
    }

    private void setlistener() {
        mIvClear.setOnClickListener(this);
        mSaw.setOnClickListener(this);
        mIvHomeLeft.setOnClickListener(this);
        mTvChangePassword.setOnClickListener(this);
        mTvZhuce.setOnClickListener(this);
        mDlBtn.setOnClickListener(this);
        mEtZh.addTextChangedListener(this);
        mEtMm.addTextChangedListener(this);
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

        mDlBtn.setEnabled(false);
        mRlLeft.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.dl);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_clear:
                mEtMm.setText("");
                break;
            case R.id.saw:
                if (IsShow) {
//                    密码不可见
                    mEtMm.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    IsShow = false;
                } else {
//                    密码可见
                    mEtMm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    IsShow = true;
                }
                break;
            case R.id.iv_home_left:

                break;

            case R.id.tv_change_password:
                goToActivity(ChangeActivity.class);
                break;
            case R.id.tv_zhuce:
                goToActivity(RegisterActivity.class);
                break;

            case R.id.dl_btn:
                if (!isOpen){
                    goToNextActivity(GestureTipActivity.class);
                }else {
                    goToNextActivity(MainActivity.class);
                }
                break;
        }

    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(mEtZh.getText().toString())
                && !TextUtils.isEmpty(mEtMm.getText().toString())) {
            mDlBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
            mDlBtn.setBackgroundResource(R.drawable.dl_btn_bg);
            mDlBtn.setEnabled(true);
        } else {
            mDlBtn.setTextColor(getResources().getColor(R.color.bsix));
            mDlBtn.setBackgroundResource(R.drawable.rect_efour);
            mDlBtn.setEnabled(false);
        }
    }
}
