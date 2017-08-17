package com.jdyy.ytwp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.bean.Stock;

import java.util.List;

/**
 * Created by Administrator on 2016/8/20 0020.
 */
public class TouZiAdapter extends BaseAdapter {
    private ViewHolder holder;
    private List<Stock> list;
    private Context mContext;

    public TouZiAdapter(Context context, List<Stock> list) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//    public Stock getData(int Position){
//        Stock stock = list.get(Position-1);
//        return stock;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_me, null);
            holder.mFhSpecies = (TextView) convertView.findViewById(R.id.fh_species);
            holder.mFhPrice = (TextView) convertView.findViewById(R.id.fh_price);
            holder.mFhState = (ImageView) convertView.findViewById(R.id.fh_state);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Stock data = list.get(position);
        holder.mFhSpecies.setText(data.getName());
        final String mState = data.getState();
//            Log.e("mState","9999999999999--------->"+mState);
        if (mState.equals("0")) {
            holder.mFhState.setImageResource(R.mipmap.wd_mn);
            holder.mFhPrice.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.mFhPrice.setText("+" + data.getPrice());
        } else if (mState.equals("1")) {
            holder.mFhState.setImageResource(R.mipmap.wd_qp);
            holder.mFhPrice.setText("——");
        } else if (mState.equals("2")) {
            holder.mFhState.setImageResource(R.mipmap.wd_pc);
            holder.mFhPrice.setText("——");
        } else if (mState.equals("3")) {
            holder.mFhState.setImageResource(R.mipmap.wd_cy);
            holder.mFhPrice.setTextColor(mContext.getResources().getColor(R.color.green));
            holder.mFhPrice.setText("-" + data.getPrice());
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView mFhSpecies;
        TextView mFhPrice;
        ImageView mFhState;
    }

}
