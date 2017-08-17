package com.jdyy.ytwp.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.adapter.CashAdapter;
import com.jdyy.ytwp.bean.Cash;
import com.jdyy.ytwp.views.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 银币商城界面
 * Created by Administrator on 2016/8/29 0029.
 */
public class SilverMallActivity extends BaseActivity {
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
    @Bind(R.id.xLvCash)
    XListView mXLvCash;

    List<Cash> mCashs = new ArrayList<>();
    private CashAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silvermall);
        ButterKnife.bind(this);
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

        mRlLeft.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.ybsc);
        mIvHomeLeft.setVisibility(View.VISIBLE);

        mCashs = getCashs();
        mAdapter = new CashAdapter(SilverMallActivity.this, mCashs, "1");
        mXLvCash.setAdapter(mAdapter);
        mXLvCash.setPullLoadEnable(false);
        mXLvCash.setPullRefreshEnable(false);

        mXLvCash.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cash cash = mCashs.get(position-1);
                Intent intent = new Intent(SilverMallActivity.this,MallForActivity.class);
                intent.putExtra("cash",cash);
                startActivity(intent);
            }
        });

        mIvHomeLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public List<Cash> getCashs() {
        List<Cash> list = new ArrayList<>();
        Cash cash1 = new Cash("10元", "266", "1天");
        Cash cash2 = new Cash("20元", "266", "1天");
        Cash cash3 = new Cash("50元", "266", "1天");
        Cash cash4 = new Cash("100元", "266", "1天");

        list.add(cash1);
        list.add(cash2);
        list.add(cash3);
        list.add(cash4);
        return list;
    }


}
