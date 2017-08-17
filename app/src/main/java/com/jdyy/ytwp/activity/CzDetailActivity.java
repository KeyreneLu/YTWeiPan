package com.jdyy.ytwp.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.utils.TextDealUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 充值详情界面
 * Created by Administrator on 2016/8/19 0019.
 */
public class CzDetailActivity extends BaseActivity {
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tv_right1)
    TextView mTvRight1;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.tv_balance)
    TextView mTvBalance;
    @Bind(R.id.progress1)
    View mProgress1;
    @Bind(R.id.iv_time2)
    ImageView mIvTime2;
    @Bind(R.id.tv_time2)
    TextView mTvTime2;
    @Bind(R.id.progress2)
    View mProgress2;
    @Bind(R.id.iv_time3)
    ImageView mIvTime3;
    @Bind(R.id.tv_time3)
    TextView mTvTime3;
    @Bind(R.id.progress3)
    View mProgress3;

    private String sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czdetail);
        ButterKnife.bind(this);
        sum = getIntent().getStringExtra("sum");
        initView();
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

        mRlRight.setVisibility(View.VISIBLE);
        mTvRight1.setTextColor(getResources().getColor(R.color.colorPrimary));
        mTitle.setText(R.string.jgxq);
        mTvRight1.setText(R.string.wc);
        mTvRight1.setVisibility(View.VISIBLE);

        mTvBalance.setText("成功转入" + TextDealUtils.MoneyDeal(sum) + "元");
    }
}
