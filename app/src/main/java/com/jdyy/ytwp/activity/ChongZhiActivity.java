package com.jdyy.ytwp.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 充值界面
 * Created by Administrator on 2016/8/17 0017.
 */
public class ChongZhiActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.iv_home_left)
    ImageView mIvHomeLeft;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.et_number)
    EditText mEtNumber;
    @Bind(R.id.tx_phone)
    TextView mTxPhone;
    @Bind(R.id.tx_name)
    TextView mTxName;
    @Bind(R.id.tx_card)
    TextView mTxCard;
    @Bind(R.id.btn_cz)
    Button mBtnCz;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;

    int num1 = 6;

    int num;
    View mRootView;
    private LayoutInflater mInflater;
    View mView;
    //dialog里面的控件
    private EditText mEtMima;
    private TextView mTvForget;
    private ImageView mIvDelete;
    private Button mBtnCancel, mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi);
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
        mTitle.setText(R.string.cz);
        mIvHomeLeft.setVisibility(View.VISIBLE);
    }


    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mBtnCz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_home_left:
                mInflater = LayoutInflater.from(ChongZhiActivity.this);
                mView = mInflater.inflate(R.layout.alert_dialog_text, null);
                AlertDialog dialog = new AlertDialog.Builder(ChongZhiActivity.this, R.style.MyDialog)
                        .setTitle(R.string.ts)
                        .setView(mView)
                        .setCancelable(false)
                        .setPositiveButton("确认退出", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ChongZhiActivity.this.finish();
                            }
                        }).setNegativeButton("继续充值", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;

            case R.id.btn_cz:
                if (TextUtils.isEmpty(mEtNumber.getText().toString())) {
                    T.showShort(ChongZhiActivity.this, "您还未输入充值金额，请输入");
                } else {
                    ShowDialog();
                }
                break;
        }

    }

    //输入密码框
    private void ShowDialog() {

        mInflater = LayoutInflater.from(ChongZhiActivity.this);
        mRootView = mInflater.inflate(R.layout.cz_layout_dialog, null);
        //必须要加style，不然会有白色头部
        final Dialog mDialog = new Dialog(ChongZhiActivity.this, R.style.Dialog);

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
                imm.showSoftInput(mEtMima, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        mDialog.show();
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
                if (TextUtils.isEmpty(mEtMima.getText().toString())) {
                    T.showShort(ChongZhiActivity.this, "密码为空,请输入");
                } else {
                    Intent intent = new Intent(ChongZhiActivity.this, CzDetailActivity.class);
                    intent.putExtra("sum", mEtNumber.getText().toString());
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
}
