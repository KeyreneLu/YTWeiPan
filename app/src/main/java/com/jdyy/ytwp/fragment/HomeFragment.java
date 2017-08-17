package com.jdyy.ytwp.fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.activity.MainActivity;
import com.jdyy.ytwp.activity.NewHandActivity;
import com.jdyy.ytwp.activity.NoticeActivity;
import com.jdyy.ytwp.adapter.NewsAdapter;
import com.jdyy.ytwp.bean.News;
import com.jdyy.ytwp.views.ImageCycleView;
import com.jdyy.ytwp.views.XListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, XListView.IXListViewListener {


    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.ad_view)
    ImageCycleView mAdView;
    @Bind(R.id.viewflipper)
    ViewFlipper mFlipper;
    @Bind(R.id.iv_news)
    ImageView mIvNews;
    @Bind(R.id.iv_exist)
    ImageView mIvExist;
    @Bind(R.id.xlv_news)
    XListView mXlvNews;
    private ArrayList<String> mDataList = new ArrayList<>();

    private ArrayList<String> mImageUrl = null;
    private View header;
    private LayoutInflater mInflater;

    List<News> mNewsList = new ArrayList<>();
    private NewsAdapter mAdapter;
    private Handler mHandler;
    private int start = 0;
    private int xListStatus = 0;

    private RelativeLayout mRlBtn1, mRlBtn2, mRlBtn3, mRlBtn4;
    private TextView mTvLoginSum, mTvProfitSpace, mTvMostProfit, mTvMoreNews;
    private ImageView mIvMoreNews;
    private String[] imageUrls = {"http://images.17173.com/2012/news/2012/07/02/gxy0702dp05s.jpg",
            "http://images.17173.com/2013/news/2013/08/28/mj0828cn05s.jpg",
            "http://images.17173.com/2012/news/2012/12/20/mj1220fb03s.jpg",
            "http://a.hiphotos.baidu.com/zhidao/pic/item/dcc451da81cb39db5699a55ed3160924ab183059.jpg"};
    private FragmentManager fm;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    private void httpRecord() {
        mDataList.add("暂无任何记录");
        mDataList.add("暂无任何记录2");
        addDataToView();

        mNewsList = getNewsData();
    }

    private List<News> getNewsData() {
        List<News> data = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            News news = new News("", "天气真好", "马上中秋了", "2016-9-10 17:25");
            data.add(news);
        }
        return data;
    }

    private void addDataToView() {
        for (String text : mDataList) {
            TextView tv = new TextView(getActivity());
            tv.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            tv.setTextSize(14);
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setText(text);
            mFlipper.addView(tv);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        httpRecord();
        initView();
        setlistener();
        return rootView;
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.lucency);
        }
        Resources resource = getActivity().getBaseContext().getResources();
        ColorStateList csl = resource.getColorStateList(R.color.zc_bg);
        if (csl != null) {
            mTitle.setTextColor(csl);
        }
        mRlHead.setBackgroundResource(R.color.lucency);
        mTitle.setText(R.string.app_name);
        mHandler = new Handler();
        //list添加头部,添加头布局，需要在setadapter之前使用
        if (mInflater == null) {
            mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        header = mInflater.inflate(R.layout.news_head, null, false);
        mRlBtn1 = (RelativeLayout) header.findViewById(R.id.rl_btn1);
        mRlBtn2 = (RelativeLayout) header.findViewById(R.id.rl_btn2);
        mRlBtn3 = (RelativeLayout) header.findViewById(R.id.rl_btn3);
        mRlBtn4 = (RelativeLayout) header.findViewById(R.id.rl_btn4);
        mTvLoginSum = (TextView) header.findViewById(R.id.tv_login_sum);
        mTvProfitSpace = (TextView) header.findViewById(R.id.tv_profit_space);
        mTvMostProfit = (TextView) header.findViewById(R.id.tv_most_profit);
        mTvMoreNews = (TextView) header.findViewById(R.id.tv_more_news);
        mIvMoreNews = (ImageView) header.findViewById(R.id.iv_more_news);
        mRlBtn1.setOnClickListener(this);
        mRlBtn2.setOnClickListener(this);
        mRlBtn3.setOnClickListener(this);
        mRlBtn4.setOnClickListener(this);
        mIvMoreNews.setOnClickListener(this);
        mTvMoreNews.setOnClickListener(this);
        mXlvNews.addHeaderView(header, null, false);

        mXlvNews.setPullLoadEnable(true);
        mXlvNews.setXListViewListener(this);
        mAdapter = new NewsAdapter(getActivity(), mNewsList);
        mXlvNews.setAdapter(mAdapter);
        //将图片存入数组中
        mImageUrl = new ArrayList<String>();
        for (String str : imageUrls) {
            mImageUrl.add(str);
        }
        //初始化图片，将图片显示出来
        mAdView.setImageResources(mImageUrl, mAdCycleViewListener);
        //滑动textView
        mFlipper.setFlipInterval(5000);
        mFlipper.setInAnimation(getActivity(), R.anim.slide_top_in);
        mFlipper.setOutAnimation(getActivity(), R.anim.slide_bottom_out);
        mFlipper.setAutoStart(true);
    }

    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {

        @Override
        public void onImageClick(int position, View imageView) {
            Toast.makeText(getActivity(), "position->" + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            ImageLoader.getInstance().displayImage(imageURL, imageView);// 此处本人使用了ImageLoader对图片进行加装！
        }
    };

    private void setlistener() {
        mIvNews.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        mAdView.startImageCycle();
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdView.pushImageCycle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_news:
                goToActivity(NoticeActivity.class);
                break;
            case R.id.rl_btn1:
                final MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setFragment2Fragment(new MainActivity.Fragment2Fragment() {
                    @Override
                    public void gotoFragment(ViewPager viewPager, RadioGroup radioGroup, View view, RadioButton radioButton) {
                        viewPager.setCurrentItem(1);
                        radioGroup.setBackgroundResource(R.color.textcolor);
                        view.setBackgroundResource(R.color.color78);
                        radioButton.setChecked(true);
                    }
                });
                mainActivity.forSkip();
                break;
            case R.id.rl_btn2:

                break;
            case R.id.rl_btn3:
                break;
            case R.id.rl_btn4:
                goToActivity(NewHandActivity.class);
                break;
            case R.id.tv_more_news:
            case R.id.iv_more_news:

                break;
        }
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = ++xListStatus;
                mNewsList.clear();
                mNewsList.addAll(getNewsData());
                mAdapter = new NewsAdapter(getActivity(), mNewsList);
                mXlvNews.setAdapter(mAdapter);
                onLoad();

            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mNewsList.addAll(getNewsData());
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 1000);
    }

    private void onLoad() {
        mXlvNews.stopRefresh();
        mXlvNews.stopLoadMore();
    }
}
