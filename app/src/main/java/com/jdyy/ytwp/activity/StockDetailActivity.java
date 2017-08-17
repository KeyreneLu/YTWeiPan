package com.jdyy.ytwp.activity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.bean.Stock;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 手动平仓界面
 * Created by Administrator on 2016/8/22 0022.
 */
public class StockDetailActivity extends BaseActivity implements View.OnClickListener {

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
    @Bind(R.id.tv_order)
    TextView mTvOrder;
    @Bind(R.id.tv_sum)
    TextView mTvSum;
    @Bind(R.id.tv_way)
    TextView mTvWay;
    @Bind(R.id.tv_profit)
    TextView mTvProfit;
    @Bind(R.id.tv_buying)
    TextView mTvBuying;
    @Bind(R.id.tv_hold)
    TextView mTvHold;
    @Bind(R.id.tv_occupy)
    TextView mTvOccupy;
    @Bind(R.id.tv_than)
    TextView mTvThan;
    @Bind(R.id.et_checkPrice)
    EditText mEtCheckPrice;
    @Bind(R.id.et_lossPrice)
    EditText mEtLossPrice;
    @Bind(R.id.btn_unwind)
    Button mBtnUnwind;
    @Bind(R.id.btn_change)
    Button mBtnChange;
    @Bind(R.id.btn_lossPrice)
    Button mBtnLossPrice;

    private Stock stock;
    private boolean isEdited = false;
    private boolean isEdited2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockdetail);
        ButterKnife.bind(this);
        stock = getIntent().getParcelableExtra("Stock");
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
        mTitle.setText(stock.getName());
        mIvHomeLeft.setVisibility(View.VISIBLE);

    }

    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mBtnChange.setOnClickListener(this);
        mBtnUnwind.setOnClickListener(this);
        mBtnLossPrice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_left:
                finish();
                break;
            case R.id.btn_change:
                if (!isEdited) {
                    mEtCheckPrice.setEnabled(true);
                    mEtCheckPrice.requestFocus();
                    openKeyboard();
                    mBtnChange.setBackgroundResource(R.drawable.rect_gray);
                    mBtnChange.setText(R.string.wc);
                    isEdited = true;
                } else {
                    mEtCheckPrice.setEnabled(false);
                    mBtnChange.setBackgroundResource(R.drawable.rect_green);
                    mBtnChange.setText(R.string.xg);
                    isEdited = false;
                }
                break;

            case R.id.btn_unwind:

                break;

            case R.id.btn_lossPrice:
                if (!isEdited2) {
                    mEtLossPrice.setEnabled(true);
                    mEtLossPrice.requestFocus();
                    openKeyboard();
                    mBtnLossPrice.setBackgroundResource(R.drawable.rect_gray);
                    mBtnLossPrice.setText(R.string.wc);
                    isEdited2 = true;
                } else {
                    mEtLossPrice.setEnabled(false);
                    mBtnLossPrice.setBackgroundResource(R.drawable.rect_green);
                    mBtnLossPrice.setText(R.string.xg);
                    isEdited2 = false;
                }
                break;
        }

    }

    /**
     * 打开软键盘
     */
    private void openKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
