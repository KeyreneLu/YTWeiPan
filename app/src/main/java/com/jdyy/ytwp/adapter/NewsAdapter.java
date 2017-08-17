package com.jdyy.ytwp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.bean.News;

import java.util.List;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class NewsAdapter extends BaseAdapter {

    private Context mContext;
    private List<News> data;
    private LayoutInflater mInflater;
    private ViewHolder mHolder;
    public NewsAdapter(Context context,List<News> data) {
        this.mContext = context;
        this.data = data;
        mInflater = LayoutInflater.from(context);
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
        if (convertView == null){
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_news,null);
            mHolder.mIvNewsPicture = (ImageView) convertView.findViewById(R.id.iv_news_picture);
            mHolder.mIvNewsState = (ImageView) convertView.findViewById(R.id.iv_news_state);
            mHolder.mTvNewsTitle = (TextView) convertView.findViewById(R.id.tv_news_title);
            mHolder.mTvNewsContent = (TextView) convertView.findViewById(R.id.tv_news_content);
            mHolder.mTvNewsDate = (TextView) convertView.findViewById(R.id.tv_news_date);
            if (position == 0){
                mHolder.mIvNewsState.setVisibility(View.VISIBLE);
            }else {
                mHolder.mIvNewsState.setVisibility(View.INVISIBLE);
            }
            convertView.setTag(mHolder);
        }else {
            mHolder = (ViewHolder) convertView.getTag();
            if (position == 0){
                mHolder.mIvNewsState.setVisibility(View.VISIBLE);
            }else {
                mHolder.mIvNewsState.setVisibility(View.INVISIBLE);
            }

        }
        News news = data.get(position);
        mHolder.mTvNewsTitle.setText(news.getTitle());
        mHolder.mTvNewsContent.setText(news.getContent());
        mHolder.mTvNewsDate.setText(news.getDate());
        return convertView;
    }

    class ViewHolder{
        ImageView mIvNewsPicture,mIvNewsState;
        TextView mTvNewsTitle,mTvNewsContent,mTvNewsDate;
    }
}
