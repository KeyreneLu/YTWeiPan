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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.activity.InvestDetailActivity;
import com.jdyy.ytwp.adapter.InvestAdapter;
import com.jdyy.ytwp.bean.Invest;
import com.jdyy.ytwp.views.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class InvestFragment extends BaseFragment implements XListView.IXListViewListener {

    @Bind(R.id.alv_invest)
    XListView mXlvInvest;
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;

    private InvestAdapter mAdapter;
    List<Invest> data = new ArrayList<>();

    //    是否显示更多内容
    private int xListStatus = 0;
    private int start = 0;
    Handler mHandler;
    int mSelect = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.textcolor);
        }

        Resources resource = getActivity().getBaseContext().getResources();
        ColorStateList csl = resource.getColorStateList(R.color.colorPrimary);
        if (csl != null) {
            mTitle.setTextColor(csl);
        }
        mRlHead.setBackgroundResource(R.color.textcolor);
        mTitle.setText(R.string.market);

        data = getData();
        mAdapter = new InvestAdapter(getActivity(), data);
        mXlvInvest.setAdapter(mAdapter);
        mXlvInvest.setPullLoadEnable(true);
        mXlvInvest.setXListViewListener(this);
        mXlvInvest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),InvestDetailActivity.class);
                intent.putExtra("invest",data.get(position-1));
                getActivity().startActivity(intent);
            }
        });
//        mXlvInvest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == mSelect) {
//                    mSelect = -1;
//                } else {
//                    mSelect = position;
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        mXlvInvest.setSelectionFromTop(mSelect-1,20);
//                    }
//                }
//                mAdapter.getClickId(mSelect);
//                mAdapter.notifyDataSetChanged();
//            }
//        });
        mHandler = new Handler();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public List<Invest> getData() {
        List<Invest> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int price = (int) (Math.random() * 5000);
            Invest testData = new Invest("石油" + i, "" + (int) (Math.random() * 2), "" + price, "" + price);
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
                mAdapter = new InvestAdapter(getActivity(), data);
                mXlvInvest.setAdapter(mAdapter);
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
        mXlvInvest.stopRefresh();
        mXlvInvest.stopLoadMore();
    }

}
