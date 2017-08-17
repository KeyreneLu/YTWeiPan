package com.jdyy.ytwp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.bean.Invest;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class InvestAdapter extends BaseAdapter{

    private Context mContext;
    public List<Invest> mInvestList;
    private ViewHolder mHolder;
    public InvestAdapter() {
    }

    public InvestAdapter(Context context, List<Invest> list) {
        this.mContext = context;
        this.mInvestList = list;
    }


    @Override
    public int getCount() {
        return mInvestList.size();
    }

    @Override
    public Object getItem(int position) {
        return mInvestList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_invest, null);
            mHolder.mInvestName = (TextView) convertView.findViewById(R.id.Invest_name);
            mHolder.mTvRose = (TextView) convertView.findViewById(R.id.tv_rose);
            mHolder.mTvFall = (TextView) convertView.findViewById(R.id.tv_fall);
            mHolder.mFiState = (ImageView) convertView.findViewById(R.id.fi_state);
            mHolder.mInvestState = (ImageView) convertView.findViewById(R.id.Invest_state);
            mHolder.mRlDetail = (RelativeLayout) convertView.findViewById(R.id.rl_detail);
            mHolder.mRlTop = (RelativeLayout) convertView.findViewById(R.id.rl_top);
            if (position == 0) {
                mHolder.mFiState.setVisibility(View.VISIBLE);
            } else {
                mHolder.mFiState.setVisibility(View.INVISIBLE);
            }
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
            if (position == 0) {
                mHolder.mFiState.setVisibility(View.VISIBLE);
            } else {
                mHolder.mFiState.setVisibility(View.INVISIBLE);
            }
        }
        Invest invest = mInvestList.get(position);
        mHolder.mInvestName.setTag(position);
        mHolder.mInvestName.setText(invest.getName());
        if (invest.getState().equals("0")) {
            mHolder.mInvestState.setImageResource(R.mipmap.cp_xd);
            mHolder.mTvRose.setText(invest.getRose());
            mHolder.mTvRose.setTextColor(mContext.getResources().getColor(R.color.green));
            mHolder.mTvFall.setText(invest.getFall());
            mHolder.mTvFall.setTextColor(mContext.getResources().getColor(R.color.green));
        } else {
            mHolder.mInvestState.setImageResource(R.mipmap.cp_sz);
            mHolder.mTvRose.setText(invest.getRose());
            mHolder.mTvRose.setTextColor(mContext.getResources().getColor(R.color.red));
            mHolder.mTvFall.setText(invest.getFall());
            mHolder.mTvFall.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        return convertView;
    }
//    /**
//     * 显示隐藏股票趋势
//     */
//    class OnLvItemClickListener implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v) {
//            int tag = (int) v.getTag();
//            /**
//             * 当部分隐藏的时候，将圆点清0
//             */
//            flag =0;
//            //如果当前项为展开，则将其置为-1，目的是为了让其隐藏，如果当前项为隐藏，则将当前位置设置给全局变量，让其展开，这也就是借助于中间变量实现布局的展开与隐藏
//            if (tag == mSelect) {
//                mSelect = -1;
//            } else {
//                mSelect = tag;
//            }
//            notifyDataSetChanged();
//        }
//    }
    private static class ViewHolder {
        TextView mInvestName;
        TextView mTvRose;
        TextView mTvFall;
        ImageView mFiState, mInvestState;
        RelativeLayout mRlTop, mRlDetail;
    }

}
