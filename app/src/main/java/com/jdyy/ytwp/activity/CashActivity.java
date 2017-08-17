package com.jdyy.ytwp.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.bean.Cash;
import com.jdyy.ytwp.views.XListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 抵价券界面
 * Created by Administrator on 2016/8/26 0026.
 */
public class CashActivity extends BaseActivity implements XListView.IXListViewListener, View.OnClickListener {
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
    @Bind(R.id.lv_cash)
    XListView mLvCash;
    @Bind(R.id.iv_nothing)
    ImageView mIvNothing;
    @Bind(R.id.tv_nothing)
    TextView mTvNothing;
    @Bind(R.id.tv_convert)
    TextView mTvConvert;
    @Bind(R.id.tv_right1)
    TextView mTvRight1;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.rl_nodata)
    RelativeLayout mRlNodata;

    private Cash cash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        ButterKnife.bind(this);
        httpload();
        initView();
        setlistener();
    }

    private void httpload() {

    }

    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mTvRight1.setOnClickListener(this);
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
        mRlRight.setVisibility(View.VISIBLE);
        mTvRight1.setVisibility(View.VISIBLE);
        mTvRight1.setText("说明");
        mTvRight1.setTextColor(getResources().getColor(R.color.colorPrimary));
        mTitle.setText(R.string.xjj);
        mIvHomeLeft.setVisibility(View.VISIBLE);

        mLvCash.setPullLoadEnable(true);
        mLvCash.setXListViewListener(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_left:
                Intent intent = new Intent();
                intent.putExtra("cash", "6");
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.tv_right1:
                Intent intent1 = new Intent(CashActivity.this,CashDeclareActivity.class);
                intent1.putExtra("flag","0");
                startActivity(intent1);
                break;
        }
    }
}
