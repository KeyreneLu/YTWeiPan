package com.jdyy.ytwp.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.adapter.SpeciesAdapter;
import com.jdyy.ytwp.utils.DateUtils;
import com.jdyy.ytwp.views.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 账单界面
 * Created by Administrator on 2016/8/18 0018.
 */
public class ZhangDanActivity extends BaseActivity implements View.OnClickListener {
    View popupView;

    //    获得全部种类
    String[] mSpecies;
    //    获得时间
    String[] mMonths;

    ListView mLvSpecies;
    ListView mLvYear;
    ListView mLvMonth, mLvDay;
    TextView mTvMonth, mTvDay;
    PopupWindow mPopupWindow;
    SpeciesAdapter mAdapter;
    SpeciesAdapter mAdapter2;
    SpeciesAdapter mAdapter3;

    @Bind(R.id.zd_xian2)
    View mZdXian2;
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
    @Bind(R.id.tv_record_day)
    TextView mTvRecordDay;
    @Bind(R.id.tv_record_week)
    TextView mTvRecordWeek;
    @Bind(R.id.tv_record_month)
    TextView mTvRecordMonth;
    @Bind(R.id.tv_record_mine)
    TextView mTvRecordMine;
    @Bind(R.id.tv_date_begin)
    TextView mTvDateBegin;
    @Bind(R.id.iv_date_begin)
    ImageView mIvDateBegin;
    @Bind(R.id.rl_date_begin)
    RelativeLayout mRlDateBegin;
    @Bind(R.id.tv_date_end)
    TextView mTvDateEnd;
    @Bind(R.id.iv_date_end)
    ImageView mIvDateEnd;
    @Bind(R.id.rl_date_end)
    RelativeLayout mRlDateEnd;
    @Bind(R.id.tv_date_sure)
    TextView mTvDateSure;
    @Bind(R.id.ll_mine)
    LinearLayout mLlMine;
    @Bind(R.id.ll_btn)
    RelativeLayout mLlBtn;
    @Bind(R.id.rv_record)
    XListView mRvRecord;
    @Bind(R.id.ll_date)
    LinearLayout mLlDate;
    //    得到当前的年，月
    private int mCurrentYear;
    private int mCurrentMonth;
    private int mCurrentDay;

    List<String> yearList;
    List<String> monthList;
    List<String> monthList2;
    List<String> SpeciesList;
    List<String> dayList;

    private String year;
    boolean isFirst = true;
    private int yearPos = 0;
    private int monthPos = 0;
    private int dayPos = 0;

    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhangdan);
        ButterKnife.bind(this);
        initData();
        initView();
        setlistener();
    }

    private void initData() {
        SpeciesList = new ArrayList<>();
        monthList2 = new ArrayList<>();
        mSpecies = getResources().getStringArray(R.array.Species);
        mMonths = getResources().getStringArray(R.array.Month);

        for (int i = 0; i < mSpecies.length; i++) {
            SpeciesList.add(mSpecies[i]);
        }
        for (int i = 0; i < mMonths.length; i++) {
            monthList2.add(mMonths[i]);
        }
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
        mTitle.setText(R.string.zd);
        mIvHomeLeft.setVisibility(View.VISIBLE);
        mTvDateBegin.setText(DateUtils.getCurrentDate());
        mTvDateEnd.setText(DateUtils.getCurrentDate());
    }


    private void setlistener() {
        mIvHomeLeft.setOnClickListener(this);
        mTvRecordDay.setOnClickListener(this);
        mTvRecordWeek.setOnClickListener(this);
        mTvRecordMonth.setOnClickListener(this);
        mTvRecordMine.setOnClickListener(this);
        mRlDateBegin.setOnClickListener(this);
        mRlDateEnd.setOnClickListener(this);
        mTvDateSure.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_record_day:
                mTvRecordDay.setTextColor(getResources().getColor(R.color.colorPrimary));
                mTvRecordWeek.setTextColor(getResources().getColor(R.color.dl_bg));
                mTvRecordMonth.setTextColor(getResources().getColor(R.color.dl_bg));
                mTvRecordDay.setBackgroundResource(R.drawable.rect_full_yellow);
                mTvRecordWeek.setBackgroundResource(R.drawable.rect_middle_yellow);
                mTvRecordMonth.setBackgroundResource(R.drawable.rect_middle_yellow);
                break;
            case R.id.tv_record_week:
                mTvRecordWeek.setTextColor(getResources().getColor(R.color.colorPrimary));
                mTvRecordDay.setTextColor(getResources().getColor(R.color.dl_bg));
                mTvRecordMonth.setTextColor(getResources().getColor(R.color.dl_bg));
                mTvRecordDay.setBackgroundResource(R.drawable.rect_left_yellow);
                mTvRecordWeek.setBackgroundResource(R.color.dl_bg);
                mTvRecordMonth.setBackgroundResource(R.drawable.rect_middle_yellow);
                break;
            case R.id.tv_record_month:
                mTvRecordDay.setTextColor(getResources().getColor(R.color.dl_bg));
                mTvRecordWeek.setTextColor(getResources().getColor(R.color.dl_bg));
                mTvRecordMonth.setTextColor(getResources().getColor(R.color.colorPrimary));
                mTvRecordDay.setBackgroundResource(R.drawable.rect_left_yellow);
                mTvRecordWeek.setBackgroundResource(R.drawable.rect_middle_yellow);
                mTvRecordMonth.setBackgroundResource(R.color.dl_bg);
                break;
            case R.id.tv_record_mine:
                mLlMine.setVisibility(View.VISIBLE);
                mLlDate.setVisibility(View.GONE);
                break;
            case R.id.tv_date_sure:
                break;
            case R.id.rl_date_begin:
                state = 0;
                mIvDateBegin.setBackgroundResource(R.mipmap.wd_xcjt);
                mTvDateBegin.setTextColor(getResources().getColor(R.color.dl_bg));
                showDatePopuWindows();
                break;
            case R.id.rl_date_end:
                state = 1;
                mIvDateEnd.setBackgroundResource(R.mipmap.wd_xcjt);
                mTvDateEnd.setTextColor(getResources().getColor(R.color.dl_bg));
                showDatePopuWindows();
                break;
            case R.id.iv_home_left:
                finish();
                break;
        }

    }

    //时间的弹窗
    private void showDatePopuWindows() {
        popupView = getLayoutInflater().inflate(R.layout.popu_date, null, false);
        mLvYear = (ListView) popupView.findViewById(R.id.lv_year);
        mLvMonth = (ListView) popupView.findViewById(R.id.lv_month);
        mTvMonth = (TextView) popupView.findViewById(R.id.tv_month);
        mTvDay = (TextView) popupView.findViewById(R.id.tv_day);
        mLvDay = (ListView) popupView.findViewById(R.id.lv_day);
        mCurrentYear = Integer.parseInt(DateUtils.getCurrentYear());
        mCurrentMonth = Integer.parseInt(DateUtils.getCurrentMonth());
        mCurrentDay = Integer.parseInt(DateUtils.getCurrentDay());
        year = String.valueOf(mCurrentYear);
        yearList = new ArrayList<>();
        monthList = new ArrayList<>();
        dayList = new ArrayList<>();

        for (int i = mCurrentYear; i >= 2014; i--) {
            yearList.add(String.valueOf(i));
        }

        for (int j = mCurrentMonth; j >= 1; j--) {
            monthList.add(String.valueOf(j));
        }

        for (int k = mCurrentDay; k > 0; k--) {
            dayList.add(String.valueOf(k));
        }

        mAdapter = new SpeciesAdapter(ZhangDanActivity.this, yearList, 1);
        mLvYear.setAdapter(mAdapter);
        mAdapter2 = new SpeciesAdapter(ZhangDanActivity.this, monthList, 2);
        mLvMonth.setAdapter(mAdapter2);
        mAdapter3 = new SpeciesAdapter(ZhangDanActivity.this, dayList, 3);
        mLvDay.setAdapter(mAdapter3);

        if (isFirst) {
            mAdapter.changeSelected(0);
            mAdapter2.changeMonthSelected(0);
            mAdapter3.changeDaySelected(0);
            mTvMonth.setText(mCurrentYear + "全年");
            mTvDay.setText(mCurrentMonth + "月全月");
            isFirst = false;
        } else {
            mAdapter.changeSelected(yearPos);
            int year = Integer.parseInt(yearList.get(yearPos));
            int month = Integer.parseInt(monthList.get(monthPos));
            if (year < mCurrentYear) {

                monthList.clear();
                dayList.clear();
                monthList.addAll(monthList2);
                mAdapter2.notifyDataSetChanged();
                dayList.addAll(getDaySum(year, month));
                mAdapter3.notifyDataSetChanged();
            } else {
                monthList.clear();
                dayList.clear();
                for (int j = mCurrentMonth; j >= 1; j--) {
                    monthList.add(String.valueOf(j));

                }
                mAdapter2.notifyDataSetChanged();
                if (month == mCurrentMonth) {
                    for (int j = mCurrentDay; j >= 1; j--) {
                        dayList.add(String.valueOf(j));
                    }
                    mAdapter3.notifyDataSetChanged();
                } else {
                    dayList.addAll(getDaySum(year, month));
                    mAdapter3.notifyDataSetChanged();
                }
            }
            mAdapter2.changeMonthSelected(monthPos);
            mAdapter3.changeDaySelected(dayPos);
            mTvMonth.setText(yearList.get(yearPos) + "全年");
            mTvDay.setText(monthList.get(monthPos) + "月全月");
        }
        if (state == 0) {
            mTvDateBegin.setTextColor(getResources().getColor(R.color.dl_bg));
            mIvDateBegin.setBackgroundResource(R.mipmap.wd_xcjt);
        } else {
            mTvDateEnd.setTextColor(getResources().getColor(R.color.dl_bg));
            mIvDateEnd.setBackgroundResource(R.mipmap.wd_xcjt);
        }

        mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(false);

        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAsDropDown(mZdXian2);

        mLvYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                yearPos = position;
                mAdapter.changeSelected(position);
                mAdapter2.changeMonthSelected(monthPos);
                mTvMonth.setText(yearList.get(position) + "全年");
                mTvDay.setText(monthList.get(monthPos) + "月全月");
                int year = Integer.parseInt(yearList.get(position));
                int month = Integer.parseInt(monthList.get(monthPos));
                if (year < mCurrentYear) {

                    monthList.clear();
                    dayList.clear();
                    monthList.addAll(monthList2);
                    mAdapter2.notifyDataSetChanged();
                    dayList.addAll(getDaySum(year, month));
                    mAdapter3.notifyDataSetChanged();
                } else {
                    monthList.clear();
                    dayList.clear();
                    for (int j = mCurrentMonth; j >= 1; j--) {
                        monthList.add(String.valueOf(j));
                    }
                    if (month == mCurrentMonth) {
                        for (int j = mCurrentDay; j >= 1; j--) {
                            dayList.add(String.valueOf(j));
                        }
                    } else {
                        dayList.addAll(getDaySum(year, month));
                    }
                    mAdapter2.notifyDataSetChanged();
                    mAdapter3.notifyDataSetChanged();
                }
            }
        });
        mLvMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                monthPos = position;
                mAdapter2.changeMonthSelected(position);
                mAdapter.changeSelected(yearPos);
                mTvMonth.setText(yearList.get(yearPos) + "全年");
                mTvDay.setText(monthList.get(monthPos) + "月全月");
                int year = Integer.parseInt(yearList.get(yearPos));
                int month = Integer.parseInt(monthList.get(position));
                if (year < mCurrentYear) {
                    dayList.clear();
                    dayList.addAll(getDaySum(year, month));
                    mAdapter3.notifyDataSetChanged();
                } else {
                    dayList.clear();
                    if (month == mCurrentMonth) {
                        for (int j = mCurrentDay; j >= 1; j--) {
                            dayList.add(String.valueOf(j));
                        }
                    } else {
                        dayList.addAll(getDaySum(year, month));
                    }
                    mAdapter3.notifyDataSetChanged();
                }
            }
        });
        mLvDay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dayPos = position;
                mAdapter3.changeDaySelected(position);
                if (state == 0) {
                    mTvDateBegin.setText(yearList.get(yearPos) + "/" + monthList.get(monthPos) + "/" + dayList.get(dayPos));
                } else {
                    mTvDateEnd.setText(yearList.get(yearPos) + "/" + monthList.get(monthPos) + "/" + dayList.get(dayPos));
                }
                mPopupWindow.dismiss();
            }
        });


        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (state == 0) {
                    mTvDateBegin.setTextColor(getResources().getColor(R.color.textcolor));
                    mIvDateBegin.setBackgroundResource(R.mipmap.wd_xhjt);
                } else {
                    mTvDateEnd.setTextColor(getResources().getColor(R.color.textcolor));
                    mIvDateEnd.setBackgroundResource(R.mipmap.wd_xhjt);
                }

            }
        });
    }

    private List<String> getDaySum(int year, int month) {
        List<String> listDay = new ArrayList<>();
        int mArray[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31};
        // 判断闰年的情况 ，2月份有29天；
        if ((year % 400 == 0)
                || ((year % 100 != 0) && (year % 4 == 0))) {
            mArray[1] = 29;
        }
        for (int i = mArray[month - 1]; i > 0; i--) {
            listDay.add(i + "");
        }
        return listDay;
    }

//    //种类的弹窗
//    private void showSpeciesPopuWindows() {
//        popupView = getLayoutInflater().inflate(R.layout.popu_species, null, false);
//        mLvSpecies = (ListView) popupView.findViewById(R.id.lv_species);
//        mAdapter = new SpeciesAdapter(ZhangDanActivity.this, SpeciesList, 0);
//        mLvSpecies.setAdapter(mAdapter);
//        mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
//        mPopupWindow.setTouchable(true);
//        mPopupWindow.setOutsideTouchable(true);
//
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//        mPopupWindow.showAsDropDown(mZdXian2);
//
//        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                mTvSpecies.setTextColor(getResources().getColor(R.color.textcolor));
//                mIvSpecies.setBackgroundResource(R.mipmap.wd_xhjt);
//            }
//        });
//        mLvSpecies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mTvSpecies.setText(mSpecies[position]);
//                mTvSpecies.setTextColor(getResources().getColor(R.color.textcolor));
//                mIvSpecies.setBackgroundResource(R.mipmap.wd_xhjt);
//                mPopupWindow.dismiss();
//            }
//        });
//    }


}
