package com.jdyy.ytwp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.bean.Silver;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29 0029.
 */
public class SilverAdapter extends BaseAdapter {
    private Context mContext;
    private List<Silver> data;
    private String flag;
    private String mState;

    public SilverAdapter(Context context, List<Silver> data,String flag) {
        this.mContext = context;
        this.data = data;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_record,null);
            mHolder.mTvDay = (TextView) convertView.findViewById(R.id.tv_day);
            mHolder.mTvTime = (TextView) convertView.findViewById(R.id.tv_time);
            mHolder.mTvMoney = (TextView) convertView.findViewById(R.id.tv_money);
            mHolder.mTvSource = (TextView) convertView.findViewById(R.id.tv_source);
            mHolder.mTvState = (TextView) convertView.findViewById(R.id.tv_state);
            mHolder.mIvPicture = (ImageView) convertView.findViewById(R.id.iv_picture);
            convertView.setTag(mHolder);
        }else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        Silver silver = data.get(position);
        if (flag.equals("0")){
            mHolder.mTvDay.setText(silver.getWeek());
            mHolder.mTvTime.setText(silver.getTime());
            mState = silver.getState();
            if (mState.equals("0")){
                mHolder.mTvMoney.setText("-"+silver.getSum());
            }else {
                mHolder.mTvMoney.setText("+"+silver.getSum());
            }
            mHolder.mTvSource.setText(silver.getSource());
        }else if (flag.equals("1")){
            mHolder.mIvPicture.setImageResource(R.mipmap.fx_rm);
            mHolder.mTvDay.setText(silver.getWeek());
            mHolder.mTvTime.setText(silver.getTime());
            mHolder.mTvState.setVisibility(View.VISIBLE);
            mHolder.mTvMoney.setText("+"+silver.getSum());
            mState = silver.getState();
            if (mState.equals("0")){
                mHolder.mTvState.setText(R.string.jyks);
            }else {
                mHolder.mTvState.setText(R.string.jyyl);
            }
            mHolder.mTvSource.setText(silver.getSource());
        }

        return convertView;
    }

    class ViewHolder{
        TextView mTvDay,mTvTime,mTvMoney,mTvSource,mTvState;
        ImageView mIvPicture;
    }
}
