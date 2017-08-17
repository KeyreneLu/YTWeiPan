package com.jdyy.ytwp.fragment;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.activity.ChongZhiActivity;
import com.jdyy.ytwp.activity.StockDetailActivity;
import com.jdyy.ytwp.activity.TiXianActivity;
import com.jdyy.ytwp.activity.WalletActivity;
import com.jdyy.ytwp.activity.ZhangDanActivity;
import com.jdyy.ytwp.adapter.TouZiAdapter;
import com.jdyy.ytwp.bean.Stock;
import com.jdyy.ytwp.views.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/9 0009.
 */

public class MarketFragment extends BaseFragment implements View.OnClickListener, XListView.IXListViewListener {

    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.tv_num)
    TextView mTvNum;
    @Bind(R.id.wallet_profit)
    TextView mWalletProfit;
    @Bind(R.id.tv_advance_charge)
    TextView mTvAdvanceCharge;
    @Bind(R.id.available_advance)
    TextView mAvailableAdvance;
    @Bind(R.id.tv_prepayment_rate)
    TextView mTvPrepaymentRate;
    @Bind(R.id.tv_tx)
    TextView mTvTx;
    @Bind(R.id.tv_cz)
    TextView mTvCz;
    @Bind(R.id.tv_zd)
    TextView mTvZd;
    @Bind(R.id.AL_list)
    XListView mALList;
    @Bind(R.id.fh_iv)
    ImageView mFhIv;
    @Bind(R.id.tv_zanwu)
    TextView mTvZanwu;
    @Bind(R.id.tv_business)
    TextView mTvBusiness;
    @Bind(R.id.Rl_wallet)
    RelativeLayout mRlWallet;

    private List<Stock> data = new ArrayList<>();
    private TouZiAdapter mAdapter;
    private int xListStatus = 0;

    Handler mHandler;
    private int start = 0;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initView();
        setlistener();
        return rootView;
    }
    public List<Stock> getData() {
        List<Stock> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int state = (int) (Math.random() * 4);
            Stock testData = new Stock("石油" + i, "200", "" + state);
            list.add(testData);
        }
        return list;
    }
    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.dl_bg);
        }
        Resources resource = getActivity().getBaseContext().getResources();
        ColorStateList csl = resource.getColorStateList(R.color.zc_bg);
        if (csl != null) {
            mTitle.setTextColor(csl);
        }
        mRlHead.setBackgroundResource(R.color.dl_bg);
        mTitle.setText(R.string.invest);

        data = getData();
        mAdapter = new TouZiAdapter(getActivity(), data);
        mALList.setAdapter(mAdapter);

        mALList.setPullLoadEnable(true);
        mALList.setXListViewListener(this);
        mHandler = new Handler();
        mALList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), StockDetailActivity.class);
                intent.putExtra("Stock", (Stock) mAdapter.getItem(position - 1));
                getActivity().startActivity(intent);
            }
        });
    }

    private void setlistener() {
        mTvTx.setOnClickListener(this);
        mTvCz.setOnClickListener(this);
        mTvZd.setOnClickListener(this);
        mRlWallet.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Rl_wallet:
                goToActivity(WalletActivity.class);
                break;
            case R.id.tv_tx:
                goToActivity(TiXianActivity.class);
                break;
            case R.id.tv_cz:
                goToActivity(ChongZhiActivity.class);
                break;
            case R.id.tv_zd:
                goToActivity(ZhangDanActivity.class);
                break;
        }
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = ++xListStatus;
                data.clear();
                data.addAll(getData());
                mAdapter = new TouZiAdapter(getActivity(), data);
                mALList.setAdapter(mAdapter);
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
        mALList.stopRefresh();
        mALList.stopLoadMore();
    }
}
