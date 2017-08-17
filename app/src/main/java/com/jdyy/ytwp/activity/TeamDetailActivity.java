package com.jdyy.ytwp.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.adapter.CrewAdapter;
import com.jdyy.ytwp.bean.Crew;
import com.jdyy.ytwp.views.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 队伍详情界面
 * Created by Administrator on 2016/8/30 0030.
 */
public class TeamDetailActivity extends BaseActivity implements XListView.IXListViewListener {
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
    @Bind(R.id.Xlv_crew)
    XListView mXlvCrew;

    private CrewAdapter mAdapter;
    List<Crew> data = new ArrayList<>();
    Handler mHandler;
//    Xlistview的一些初始数
    private int xListStatus = 0;
    private int start = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamdetail);
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
        mTitle.setText(R.string.dwxq);
        mIvHomeLeft.setVisibility(View.VISIBLE);
        //listview的初始化
        data = getData();
        mAdapter = new CrewAdapter(TeamDetailActivity.this,data,"0");
        mXlvCrew.setAdapter(mAdapter);
        mXlvCrew.setPullLoadEnable(true);
        mXlvCrew.setXListViewListener(this);
        mHandler = new Handler();

        mIvHomeLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public List<Crew> getData() {
        List<Crew> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int price = (int) (Math.random() * 8000);
            Crew testData = new Crew("王先生" + i,"18451244578", "" + price);
            list.add(testData);
        }
        return list;
    }

    @Override
    public void onRefresh() {
        xListStatus = 1;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = ++xListStatus;
                data.clear();
                data.addAll(getData());
                mAdapter = new CrewAdapter(TeamDetailActivity.this, data,"0");
                mXlvCrew.setAdapter(mAdapter);
                onLoad();

            }
        }, 1000);

    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                data.addAll(getData());
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 1000);

    }


    private void onLoad() {
        mXlvCrew.stopRefresh();
        mXlvCrew.stopLoadMore();
    }

}
