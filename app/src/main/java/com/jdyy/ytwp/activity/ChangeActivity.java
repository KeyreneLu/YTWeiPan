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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.utils.T;
import com.jdyy.ytwp.utils.TimeCountUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 修改密码界面
 * Created by Administrator on 2016/8/15 0015.
 */
public class ChangeActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

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
    @Bind(R.id.et_xg)
    EditText mEtXg;
    @Bind(R.id.et_yzm)
    EditText mEtYzm;
    @Bind(R.id.et_mm)
    EditText mEtMm;
    @Bind(R.id.iv_clear)
    ImageView mIvClear;
    @Bind(R.id.saw)
    ImageView mSaw;
    @Bind(R.id.ll_xy)
    LinearLayout mLlXy;
    @Bind(R.id.btn_xg)
    Button mBtnXg;
    @Bind(R.id.ib_choose)
    ImageButton mIbChoose;
    @Bind(R.id.btn_yzm)
    Button mBtnYzm;
    @Bind(R.id.tv_xieyi)
    TextView mTvXieyi;
    @Bind(R.id.tv_change_xieyi)
    TextView mTvChangeXieyi;

    //    密码是否可见
    private boolean IsShow = false;
    //    协议是否选中
    private boolean IsRead = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
        initView();
        setlistener();
    }

    private void setlistener() {
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

        mBtnXg.setEnabled(false);
        mRlLeft.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.xgmm);
        mIvHomeLeft.setVisibility(View.VISIBLE);

    }

    private void initView() {
        mIvClear.setOnClickListener(this);
        mBtnXg.setOnClickListener(this);
        mSaw.setOnClickListener(this);
        mBtnYzm.setOnClickListener(this);
        mIvHomeLeft.setOnClickListener(this);
        mTvXieyi.setOnClickListener(this);
        mIbChoose.setOnClickListener(this);
        mTvChangeXieyi.setOnClickListener(this);

        mEtXg.addTextChangedListener(this);
        mEtMm.addTextChangedListener(this);
        mEtYzm.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_clear:
                mEtMm.setText("");
                break;
            case R.id.btn_xg:
                String userPassword = mEtMm.getText().toString();
                if (userPassword.length()<6){
                    T.showShort(this,"密码长度不够");
                }else if (!isLetterDigit(userPassword)){
                    T.showShort(this,"密码必须包含数字、字母符号");
                } else {
                    goToNextActivity(MainActivity.class);
                }
                break;
            case R.id.ib_choose:
            case R.id.tv_change_xieyi:
                if (IsRead) {
                    mIbChoose.setBackgroundResource(R.mipmap.icon_dgh);
                    mBtnXg.setTextColor(getResources().getColor(R.color.bsix));
                    mBtnXg.setBackgroundResource(R.drawable.rect_efour);
                    mBtnXg.setEnabled(false);
                    IsRead = false;
                } else {
                    mIbChoose.setBackgroundResource(R.mipmap.icon_dg);
                    if (!TextUtils.isEmpty(mEtXg.getText().toString())
                            && !TextUtils.isEmpty(mEtMm.getText().toString())
                            && !TextUtils.isEmpty(mEtYzm.getText().toString())) {
                        mBtnXg.setTextColor(getResources().getColor(R.color.colorPrimary));
                        mBtnXg.setBackgroundResource(R.drawable.dl_btn_bg);
                        mBtnXg.setEnabled(true);
                    } else {
                        mBtnXg.setTextColor(getResources().getColor(R.color.bsix));
                        mBtnXg.setBackgroundResource(R.drawable.rect_efour);
                        mBtnXg.setEnabled(false);
                    }
                    IsRead = true;
                }
                break;
            case R.id.saw:
                if (IsShow) {
                    mEtMm.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    IsShow = false;
                } else {
                    mEtMm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    IsShow = true;
                }
                break;
            case R.id.btn_yzm:
                //然后在需要用这个的方法里new一个对象，然后调用start();方法就可以啦
                TimeCountUtils timeCountUtils = new TimeCountUtils(ChangeActivity.this, 60000, 1000, mBtnYzm);
                timeCountUtils.start();

                // 获取验证码
//                getVerificationCode(phoneNum);

                break;

            case R.id.iv_home_left:
                finish();
                break;

            case R.id.tv_xieyi:

                break;
        }

    }

    //密码中必须包含数字、字母符号
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            }
            if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(mEtXg.getText().toString())
                && !TextUtils.isEmpty(mEtMm.getText().toString())
                && !TextUtils.isEmpty(mEtYzm.getText().toString())
                && IsRead == true) {
            mBtnXg.setTextColor(getResources().getColor(R.color.colorPrimary));
            mBtnXg.setBackgroundResource(R.drawable.dl_btn_bg);
            mBtnXg.setEnabled(true);
        } else {
            mBtnXg.setTextColor(getResources().getColor(R.color.bsix));
            mBtnXg.setBackgroundResource(R.drawable.rect_efour);
            mBtnXg.setEnabled(false);
        }
    }
}
