package com.jdyy.ytwp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.bean.Cash;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29 0029.
 */
public class CashAdapter extends BaseAdapter {

    private List<Cash> mCashs;
    private Context mContext;
    private String flag;
    public CashAdapter(Context context,List<Cash> data,String flag) {
        this.mContext =context;
        this.mCashs = data;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return mCashs.size();
    }

    @Override
    public Object getItem(int position) {
        return mCashs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null){
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cash,null,false);
            mHolder.mTvDeadLine = (TextView) convertView.findViewById(R.id.tv_deadline);
            mHolder.mTvLose = (TextView) convertView.findViewById(R.id.tv_lose);
            mHolder.mCashMoney = (TextView) convertView.findViewById(R.id.cash_money);
            mHolder.mTvTime = (TextView) convertView.findViewById(R.id.tv_time);
            mHolder.mTvSilver = (TextView) convertView.findViewById(R.id.tv_silver);
            convertView.setTag(mHolder);
        }else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        Cash cash = mCashs.get(position);
        if (flag.equals("0")){

        }else if (flag.equals("1")){
            mHolder.mTvLose.setText("兑换需");
            mHolder.mTvSilver.setVisibility(View.VISIBLE);
            mHolder.mTvSilver.setText("银币");
            mHolder.mCashMoney.setText(cash.getsize());
            mHolder.mTvDeadLine.setText(cash.getValid());
            mHolder.mTvTime.setText(cash.getDate());
        }
        return convertView;
    }

    class ViewHolder{
        TextView mCashMoney,mTvLose,mTvTime,mTvSilver,mTvDeadLine;
    }
}
