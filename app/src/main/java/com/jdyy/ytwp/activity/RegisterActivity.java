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
 * 注册界面
 * Created by Administrator on 2016/8/15 0015.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

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
    @Bind(R.id.et_zc)
    EditText mEtZc;
    @Bind(R.id.et_yzm)
    EditText mEtYzm;
    @Bind(R.id.et_mm)
    EditText mEtMm;
    @Bind(R.id.iv_clear)
    ImageView mIvClear;
    @Bind(R.id.saw)
    ImageView mSaw;
    @Bind(R.id.et_yqm)
    EditText mEtYqm;
    @Bind(R.id.ll_xy)
    LinearLayout mLlXy;
    @Bind(R.id.btn_zc)
    Button mBtnZc;
    @Bind(R.id.btn_yzm)
    Button mBtnYzm;
    @Bind(R.id.zc_choose)
    ImageButton mZcChoose;
    @Bind(R.id.zc_xieyi)
    TextView mZcXieyi;
    @Bind(R.id.tv_register_xieyi)
    TextView mTvRegisterXieyi;

    private boolean IsShow = false;
    private boolean IsRead = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        setlistener();
    }

    private void setlistener() {
        mBtnYzm.setOnClickListener(this);
        mIvClear.setOnClickListener(this);
        mSaw.setOnClickListener(this);
        mLlXy.setOnClickListener(this);
        mZcXieyi.setOnClickListener(this);
        mBtnZc.setOnClickListener(this);
        mIvHomeLeft.setOnClickListener(this);
        mZcXieyi.setOnClickListener(this);
        mTvRegisterXieyi.setOnClickListener(this);
        mZcChoose.setOnClickListener(this);

        mEtZc.addTextChangedListener(this);
        mEtMm.addTextChangedListener(this);
        mEtYzm.addTextChangedListener(this);
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

        mBtnZc.setEnabled(false);
        mRlLeft.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.zc);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yzm:
                //然后在需要用这个的方法里new一个对象，然后调用start();方法就可以啦
                TimeCountUtils timeCountUtils = new TimeCountUtils(RegisterActivity.this, 60000, 1000, mBtnYzm);
                timeCountUtils.start();
                break;

            case R.id.iv_clear:
                mEtMm.setText("");
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
            case R.id.zc_choose:
            case R.id.tv_register_xieyi:
                if (!IsRead) {
                    mZcChoose.setBackgroundResource(R.mipmap.icon_dgh);
                    mBtnZc.setTextColor(getResources().getColor(R.color.bsix));
                    mBtnZc.setBackgroundResource(R.drawable.rect_efour);
                    mBtnZc.setEnabled(false);
                    IsRead = true;
                } else {
                    mZcChoose.setBackgroundResource(R.mipmap.icon_dg);
                    if (!TextUtils.isEmpty(mEtZc.getText().toString())
                            && !TextUtils.isEmpty(mEtMm.getText().toString())
                            && !TextUtils.isEmpty(mEtYzm.getText().toString())) {
                        mBtnZc.setTextColor(getResources().getColor(R.color.colorPrimary));
                        mBtnZc.setBackgroundResource(R.drawable.dl_btn_bg);
                        mBtnZc.setEnabled(true);
                    } else {
                        mBtnZc.setTextColor(getResources().getColor(R.color.bsix));
                        mBtnZc.setBackgroundResource(R.drawable.rect_efour);
                        mBtnZc.setEnabled(false);
                    }
                    IsRead = false;
                }
                break;

            case R.id.zc_xieyi:

                break;

            case R.id.btn_zc:
                String userPassword = mEtMm.getText().toString();
                if (userPassword.length() < 6) {
                    T.showShort(this, "密码长度不够");
                } else if (!isLetterDigit(userPassword)) {
                    T.showShort(this, "密码必须包含数字、字母符号");
                } else {
                    goToNextActivity(MainActivity.class);
                }
                break;

            case R.id.iv_home_left:
                finish();
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
        if (!TextUtils.isEmpty(mEtZc.getText().toString())
                && !TextUtils.isEmpty(mEtMm.getText().toString())
                && !TextUtils.isEmpty(mEtYzm.getText().toString())
                && IsRead == false) {
            mBtnZc.setTextColor(getResources().getColor(R.color.colorPrimary));
            mBtnZc.setBackgroundResource(R.drawable.dl_btn_bg);
            mBtnZc.setEnabled(true);
        } else {
            mBtnZc.setTextColor(getResources().getColor(R.color.bsix));
            mBtnZc.setBackgroundResource(R.drawable.rect_efour);
            mBtnZc.setEnabled(false);
        }
    }
}
