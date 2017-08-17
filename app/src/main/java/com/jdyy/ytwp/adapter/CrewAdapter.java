package com.jdyy.ytwp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.bean.Crew;

import java.util.List;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class CrewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Crew> data;
    private ViewHolder mHolder;
    private String flag;
    private LayoutInflater mInflater;
    View mRootView;
    private EditText mEtCommission;
    private TextView mTvBack,mTvTransfer;
    public CrewAdapter(Context context, List<Crew> data,String flag) {
        this.mContext = context;
        this.data = data;
        this.flag =flag;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_teamcrew,null);
            mHolder.mTvTeamName = (TextView) convertView.findViewById(R.id.tv_team_name);
            mHolder.mTvTeamMoney = (TextView) convertView.findViewById(R.id.tv_team_money);
            mHolder.mTvTeamPhone = (TextView) convertView.findViewById(R.id.tv_team_phone);
            mHolder.mTvCommission = (TextView) convertView.findViewById(R.id.tv_commission);
            convertView.setTag(mHolder);
        }else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        Crew crew = data.get(position);
        if (flag.equals("0")){
            mHolder.mTvTeamName.setText(crew.getName());
            mHolder.mTvTeamPhone.setText(crew.getPhone());
            mHolder.mTvTeamMoney.setText(crew.getBalance()+"元");
        }else if (flag.equals("1")){
            mHolder.mTvTeamMoney.setVisibility(View.GONE);
            mHolder.mTvCommission.setVisibility(View.VISIBLE);
            mHolder.mTvTeamName.setText(crew.getName());
            mHolder.mTvTeamPhone.setText(crew.getPhone());
            mHolder.mTvCommission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowCommissionDialog();
                }
            });

        }


        return convertView;
    }

    private void ShowCommissionDialog() {
        mInflater = LayoutInflater.from(mContext);
        mRootView = mInflater.inflate(R.layout.layout_dialog_commission, null);
        mTvBack = (TextView) mRootView.findViewById(R.id.tv_back);
        mTvTransfer = (TextView) mRootView.findViewById(R.id.tv_transfer);
        mEtCommission = (EditText) mRootView.findViewById(R.id.et_commission);
        //必须要加style，不然会有白色头部
        final Dialog mDialog = new Dialog(mContext, R.style.Dialog);
        mDialog.getWindow().setContentView(mRootView);
        mDialog.show();

        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mTvTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    class ViewHolder{
        TextView mTvTeamName,mTvTeamPhone,mTvTeamMoney,mTvCommission;
    }
}
