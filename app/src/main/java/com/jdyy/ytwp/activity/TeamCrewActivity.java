package com.jdyy.ytwp.activity;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by Administrator on 2016/8/30 0030.
 */
public class TeamCrewActivity extends BaseActivity implements XListView.IXListViewListener {
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
    @Bind(R.id.tv_balance)
    TextView mTvBalance;
    @Bind(R.id.btn_add_agent)
    Button mBtnAddAgent;

    private CrewAdapter mAdapter;
    List<Crew> data = new ArrayList<>();
    Handler mHandler;
    //    Xlistview的一些初始数
    private int xListStatus = 0;
    private int start = 0;

    private LayoutInflater mInflater;
    View mRootView;
    private TextView mTvSureAdd;
    private EditText mEtPhoneNumber;

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
        mTvBalance.setVisibility(View.INVISIBLE);
        mBtnAddAgent.setVisibility(View.VISIBLE);

        //listview的初始化
        data = getData();
        mAdapter = new CrewAdapter(TeamCrewActivity.this,data,"1");
        mXlvCrew.setAdapter(mAdapter);
        mXlvCrew.setPullLoadEnable(true);
        mXlvCrew.setXListViewListener(this);
        mHandler = new Handler();

        mBtnAddAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAgentDialog();
            }
        });

        mIvHomeLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ShowAgentDialog() {
        mInflater = LayoutInflater.from(TeamCrewActivity.this);
        mRootView = mInflater.inflate(R.layout.layout_dialog_contact, null);
        mEtPhoneNumber = (EditText) mRootView.findViewById(R.id.et_phone_number);
        mTvSureAdd = (TextView) mRootView.findViewById(R.id.tv_sure_add);
        //必须要加style，不然会有白色头部
        final Dialog mDialog = new Dialog(TeamCrewActivity.this, R.style.Dialog);
        mDialog.getWindow().setContentView(mRootView);
        mDialog.show();
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
                mAdapter = new CrewAdapter(TeamCrewActivity.this, data,"1");
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
