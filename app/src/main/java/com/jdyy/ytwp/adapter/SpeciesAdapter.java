package com.jdyy.ytwp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;

import java.util.List;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class SpeciesAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> datas;
    private int flag;
    private ViewHolder mHolder;
    int mSelect = 0;
    int month = 0;
    int day = 0;

    public SpeciesAdapter(Context context, List<String> datas, int flag) {
        this.mContext = context;
        this.datas = datas;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_textview, parent, false);
            mHolder.mLvItemTv = (TextView) convertView.findViewById(R.id.lv_item_tv);
            mHolder.mLvLL = (LinearLayout) convertView.findViewById(R.id.lv_ll);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        if (flag == 0) {
            mHolder.mLvItemTv.setText(datas.get(position));
        } else if (flag == 1) {
            mHolder.mLvItemTv.setText(datas.get(position) + "年");
            if (position == mSelect) {
                mHolder.mLvLL.setBackgroundResource(R.drawable.list_date_press_bg);
                mHolder.mLvItemTv.setTextColor(mContext.getResources().getColor(R.color.dl_bg));
            } else {
                mHolder.mLvLL.setBackgroundColor(mContext.getResources().getColor(R.color.zc_bg));
                mHolder.mLvItemTv.setTextColor(mContext.getResources().getColor(R.color.ccolor));
            }
        } else if (flag == 2){
            mHolder.mLvItemTv.setText(datas.get(position) + "月");
            mHolder.mLvLL.setBackgroundResource(R.color.zc_bg);
            if (position == month) {
                mHolder.mLvLL.setBackgroundResource(R.drawable.list_date_press_bg);
                mHolder.mLvItemTv.setTextColor(mContext.getResources().getColor(R.color.dl_bg));
            } else {
                mHolder.mLvLL.setBackgroundColor(mContext.getResources().getColor(R.color.zc_bg));
                mHolder.mLvItemTv.setTextColor(mContext.getResources().getColor(R.color.ccolor));
            }
        }else if (flag == 3){
            mHolder.mLvItemTv.setText(datas.get(position) + "日");
            mHolder.mLvLL.setBackgroundColor(mContext.getResources().getColor(R.color.bg));
            if (position == day) {
                mHolder.mLvItemTv.setTextColor(mContext.getResources().getColor(R.color.dl_bg));
            } else {
                mHolder.mLvItemTv.setTextColor(mContext.getResources().getColor(R.color.ccolor));
            }
        }
        return convertView;
    }

    public static class ViewHolder {
        TextView mLvItemTv;
        LinearLayout mLvLL;
    }

    public void changeSelected(int pos) { //刷新方法
        if (pos != mSelect) {
            mSelect = pos;
            notifyDataSetChanged();
        }
    }

    public void changeMonthSelected(int pos) { //刷新方法
        if (pos != month) {
            month = pos;
            notifyDataSetChanged();
        }
    }

    public void changeDaySelected(int pos) { //刷新方法
        if (pos != day) {
            day = pos;
            notifyDataSetChanged();
        }
    }

}
