package com.jdyy.ytwp.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
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
import com.jdyy.ytwp.utils.TextDealUtils;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 提现界面
 * Created by Administrator on 2016/8/17 0017.
 */
public class TiXianActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.iv_home_left)
    ImageView mIvHomeLeft;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.tx_sum)
    TextView mTxSum;
    @Bind(R.id.et_number)
    EditText mEtNumber;
    @Bind(R.id.tx_phone)
    TextView mTxPhone;
    @Bind(R.id.tx_name)
    TextView mTxName;
    @Bind(R.id.tx_card)
    TextView mTxCard;
    @Bind(R.id.tx_time)
    TextView mTxTime;
    @Bind(R.id.tx_sure)
    Button mTxSure;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;

    private float max;
    private float balance;

    private LayoutInflater mInflater;
    private View mRootView;
    //dialog里面的控件
    private EditText mEtMima;
    private TextView mTvForget;
    private ImageView mIvDelete;
    private Button mBtnCancel,mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian);
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
        mTitle.setText(R.string.tx);
        mIvHomeLeft.setVisibility(View.VISIBLE);

        mTxCard.setText(TextDealUtils.CardDeal("1234567890104512458"));
    }


    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mTxSure.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.tx_sure:
                max = Float.parseFloat(mTxSum.getText().toString());
                if (TextUtils.isEmpty(mEtNumber.getText().toString())){
                    T.showShort(TiXianActivity.this,"请输入提现金额");
                }else if (Float.parseFloat(mEtNumber.getText().toString()) == 0){
                    T.showShort(TiXianActivity.this,"提现金额不能为0");
                }else if (Float.parseFloat(mEtNumber.getText().toString()) > max){
                    T.showShort(TiXianActivity.this,"提现金额过大，请重新确认");
                }else {
                    balance = Float.parseFloat(mEtNumber.getText().toString());
                    ShowDialog();
                }
                break;
        }
    }

    private void ShowDialog() {
        mInflater = LayoutInflater.from(TiXianActivity.this);
        mRootView = mInflater.inflate(R.layout.cz_layout_dialog,null);
        //必须要加style，不然会有白色头部
        final Dialog mDialog= new Dialog(TiXianActivity.this,R.style.Dialog);
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
                    T.showShort(TiXianActivity.this,"密码为空,请输入");
                }else {
                    Intent intent = new Intent(TiXianActivity.this,TxDetailActivity.class);
                    intent.putExtra("date",getDate());
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

    public String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd   HH:mm");
        java.sql.Date curDate = new java.sql.Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
}
