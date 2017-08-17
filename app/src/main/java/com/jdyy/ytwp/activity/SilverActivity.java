package com.jdyy.ytwp.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 银币充值界面
 * Created by Administrator on 2016/8/29 0029.
 */
public class SilverActivity extends BaseActivity implements View.OnClickListener {

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
    @Bind(R.id.tv_surplus)
    TextView mTvSurplus;
    @Bind(R.id.tv_hundred)
    TextView mTvHundred;
    @Bind(R.id.rl_hundred)
    RelativeLayout mRlHundred;
    @Bind(R.id.tv_kilo)
    TextView mTvKilo;
    @Bind(R.id.rl_kilo)
    RelativeLayout mRlKilo;
    @Bind(R.id.tv_myriad)
    TextView mTvMyriad;
    @Bind(R.id.rl_myriad)
    RelativeLayout mRlMyriad;
    @Bind(R.id.et_other)
    EditText mEtOther;
    @Bind(R.id.rl_other)
    RelativeLayout mRlOther;
    @Bind(R.id.tv_cost)
    TextView mTvCost;
    @Bind(R.id.silver_choose2)
    ImageView mSilverChoose2;
    @Bind(R.id.tv_use_money)
    TextView mTvUseMoney;
    @Bind(R.id.silver_choose)
    ImageButton mSilverChoose;
    @Bind(R.id.silver_xieyi)
    TextView mSilverXieyi;
    @Bind(R.id.btn_sure_bug)
    Button mBtnSureBug;

    private int index = 1;
    private LayoutInflater mInflater;
    View mRootView;
    int num1 = 6;
    int num;
    private boolean IsChosen = true;
    //dialog里面的控件
    private EditText mEtMima;
    private TextView mTvForget;
    private ImageView mIvDelete;
    private Button mBtnCancel,mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silver);
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
        mTitle.setText(R.string.ybcz);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }


    private void setlistener() {
        mRlHundred.setOnClickListener(this);
        mRlKilo.setOnClickListener(this);
        mRlMyriad.setOnClickListener(this);
        mRlOther.setOnClickListener(this);
        mIvHomeLeft.setOnClickListener(this);
        mSilverChoose.setOnClickListener(this);
        mSilverChoose2.setOnClickListener(this);
        mBtnSureBug.setOnClickListener(this);
        mEtOther.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_hundred:
                IsChosen = true;
                mEtOther.setCursorVisible(false);
                index = 1;
                mEtOther.setText("");
                mTvCost.setText("10元");
                hideSoftInputView();
                ChangeColor(index);
                break;
            case R.id.rl_kilo:
                IsChosen = true;
                mEtOther.setCursorVisible(false);
                index = 2;
                mEtOther.setText("");
                mTvCost.setText("100元");
                hideSoftInputView();
                ChangeColor(index);
                break;
            case R.id.rl_myriad:
                IsChosen = true;
                mEtOther.setCursorVisible(false);
                index = 3;
                mEtOther.setText("");
                mTvCost.setText("1000元");
                hideSoftInputView();
                ChangeColor(index);
                break;
            case R.id.et_other:
            case R.id.rl_other:
                if (IsChosen){
                    mTvCost.setText("0"+"元");
                    IsChosen = false;
                }
                mEtOther.setCursorVisible(true);
                index = 4;
                ChangeColor(index);
                mEtOther.setFocusable(true);
                mEtOther.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!TextUtils.isEmpty(mEtOther.getText().toString())){
                            mTvCost.setText(Float.valueOf(mEtOther.getText().toString())/10+"元");
                        }else {
                            mTvCost.setText("0"+"元");
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.silver_choose:
                break;
            case R.id.silver_choose2:

                break;
            case R.id.btn_sure_bug:
                ShowDialog();
                break;
        }
    }

    private void ShowDialog() {
        mInflater = LayoutInflater.from(SilverActivity.this);
        mRootView = mInflater.inflate(R.layout.cz_layout_dialog,null);
        //必须要加style，不然会有白色头部
        final Dialog mDialog= new Dialog(SilverActivity.this,R.style.Dialog);
        mDialog.getWindow().setContentView(mRootView);
        mEtMima = (EditText) mRootView.findViewById(R.id.et_mima);
        mIvDelete = (ImageView) mRootView.findViewById(R.id.iv_delete);
        mTvForget = (TextView) mRootView.findViewById(R.id.tv_forget);
        mBtnCancel = (Button) mRootView.findViewById(R.id.btn_cancel);
        mBtnOk = (Button) mRootView.findViewById(R.id.btn_ok);

        /** 自动弹出软键盘 **/
        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput( mEtMima, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        mDialog.show();
        mEtMima.addTextChangedListener(new TextWatcher() {
            private int selectionStart;
            private int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                int number = num + s.length();
                selectionStart = mEtMima.getSelectionStart();
                selectionEnd = mEtMima.getSelectionEnd();
                if (number > num1) {
                    s.delete(selectionStart - 1, selectionEnd);
                    mEtMima.setText(s);
                    mEtMima.setSelection(mEtMima.length());//设置光标在最后
                }
            }
        });

        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtMima.setText("");
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtMima.getText().toString())){
                    T.showShort(SilverActivity.this,"密码为空,请输入");
                }else {
                    Intent intent = new Intent(SilverActivity.this,TxDetailActivity.class);
                    startActivity(intent);
                    mDialog.dismiss();
                    finish();
                }
            }
        });

        mTvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(ChangeActivity.class);
            }
        });
    }

    private void ChangeColor(int index) {
        if (index == 1){
            mRlHundred.setBackgroundResource(R.drawable.rect_yellow2);
            mTvHundred.setTextColor(getResources().getColor(R.color.dl_bg));
            mRlKilo.setBackgroundResource(R.drawable.rect_dtwo);
            mTvKilo.setTextColor(getResources().getColor(R.color.textcolor));
            mRlMyriad.setBackgroundResource(R.drawable.rect_dtwo);
            mTvMyriad.setTextColor(getResources().getColor(R.color.textcolor));
            mRlOther.setBackgroundResource(R.drawable.rect_dtwo);
            mEtOther.setTextColor(getResources().getColor(R.color.textcolor));
            mEtOther.setHintTextColor(getResources().getColor(R.color.textcolor));
        }else if (index == 2){
            mRlHundred.setBackgroundResource(R.drawable.rect_dtwo);
            mTvHundred.setTextColor(getResources().getColor(R.color.textcolor));
            mRlKilo.setBackgroundResource(R.drawable.rect_yellow2);
            mTvKilo.setTextColor(getResources().getColor(R.color.dl_bg));
            mRlMyriad.setBackgroundResource(R.drawable.rect_dtwo);
            mTvMyriad.setTextColor(getResources().getColor(R.color.textcolor));
            mRlOther.setBackgroundResource(R.drawable.rect_dtwo);
            mEtOther.setTextColor(getResources().getColor(R.color.textcolor));
            mEtOther.setHintTextColor(getResources().getColor(R.color.textcolor));
        }else if (index == 3){
            mRlHundred.setBackgroundResource(R.drawable.rect_dtwo);
            mTvHundred.setTextColor(getResources().getColor(R.color.textcolor));
            mRlKilo.setBackgroundResource(R.drawable.rect_dtwo);
            mTvKilo.setTextColor(getResources().getColor(R.color.textcolor));
            mRlMyriad.setBackgroundResource(R.drawable.rect_yellow2);
            mTvMyriad.setTextColor(getResources().getColor(R.color.dl_bg));
            mRlOther.setBackgroundResource(R.drawable.rect_dtwo);
            mEtOther.setTextColor(getResources().getColor(R.color.textcolor));
            mEtOther.setHintTextColor(getResources().getColor(R.color.textcolor));
        }else if (index == 4){
            mRlHundred.setBackgroundResource(R.drawable.rect_dtwo);
            mTvHundred.setTextColor(getResources().getColor(R.color.textcolor));
            mRlKilo.setBackgroundResource(R.drawable.rect_dtwo);
            mTvKilo.setTextColor(getResources().getColor(R.color.textcolor));
            mRlMyriad.setBackgroundResource(R.drawable.rect_dtwo);
            mTvMyriad.setTextColor(getResources().getColor(R.color.textcolor));
            mRlOther.setBackgroundResource(R.drawable.rect_yellow2);
            mEtOther.setTextColor(getResources().getColor(R.color.dl_bg));
            mEtOther.setHintTextColor(getResources().getColor(R.color.dl_bg));
        }
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
