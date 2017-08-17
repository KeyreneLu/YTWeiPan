package com.jdyy.ytwp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.adapter.InvestAdapter;
import com.jdyy.ytwp.fragment.BaseFragment;
import com.jdyy.ytwp.fragment.FindFragment;
import com.jdyy.ytwp.fragment.HomeFragment;
import com.jdyy.ytwp.fragment.InvestFragment;
import com.jdyy.ytwp.fragment.MarketFragment;
import com.jdyy.ytwp.fragment.MeFragment;
import com.jdyy.ytwp.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 主界面
 * Created by Administrator on 2016/8/16 0016.
 */
public class MainActivity extends BaseActivity {


    @Bind(R.id.vp_content)
    NoScrollViewPager mVpContent;
    @Bind(R.id.rb_home)
    RadioButton mRbHome;
    @Bind(R.id.rb_invest)
    RadioButton mRbInvest;
    @Bind(R.id.rb_find)
    RadioButton mRbFind;
    @Bind(R.id.rb_me)
    RadioButton mRbMe;
    @Bind(R.id.rg_menu)
    RadioGroup mRgMenu;
    @Bind(R.id.main_xian)
    View mMainXian;
    @Bind(R.id.rb_market)
    RadioButton mRbMarket;

    private LayoutInflater mInflater;
    List<BaseFragment> mFragments = new ArrayList<>();
    String msg;
    int First = 0;
    InvestAdapter mInvestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    //初始化Fragment
    private void initData() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("tcType", "1");
        homeFragment.setArguments(bundle1);
        mFragments.add(homeFragment);

        InvestFragment investFragment = new InvestFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("tcType", "2");
        homeFragment.setArguments(bundle2);
        mFragments.add(investFragment);

        MarketFragment marketFragment = new MarketFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString("tcType", "3");
        homeFragment.setArguments(bundle3);
        mFragments.add(marketFragment);

        FindFragment findFragment = new FindFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putString("tcType", "4");
        homeFragment.setArguments(bundle4);
        mFragments.add(findFragment);

        MeFragment meFragment = new MeFragment();
        Bundle bundle5 = new Bundle();
        bundle5.putString("tcType", "5");
        homeFragment.setArguments(bundle5);
        mFragments.add(meFragment);
    }


    private void initView() {
        TabAdapter mAdapter = new TabAdapter(getSupportFragmentManager());
        mVpContent.setOffscreenPageLimit(5);
        mVpContent.setAdapter(mAdapter);
        mVpContent.setCurrentItem(0);
//        设置ViewPager不能左右滑动
        mVpContent.setNoScroll(true);
        mInvestAdapter = new InvestAdapter();
    }


    private void initEvent() {
        mRgMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        mMainXian.setBackgroundResource(R.color.xian);
                        mRgMenu.setBackgroundResource(R.color.bg);
                        mVpContent.setCurrentItem(0, false);
                        break;
                    case R.id.rb_invest:
                        mMainXian.setBackgroundResource(R.color.ninecolor);
                        mRgMenu.setBackgroundResource(R.color.textcolor);
                        mVpContent.setCurrentItem(1, false);
                        break;
                    case R.id.rb_market:
                        mMainXian.setBackgroundResource(R.color.xian);
                        mRgMenu.setBackgroundResource(R.color.bg);
                        mVpContent.setCurrentItem(2, false);
                        break;
                    case R.id.rb_find:
                        mMainXian.setBackgroundResource(R.color.xian);
                        mRgMenu.setBackgroundResource(R.color.bg);
                        mVpContent.setCurrentItem(3, false);
                        break;
                    case R.id.rb_me:
                        mMainXian.setBackgroundResource(R.color.xian);
                        mRgMenu.setBackgroundResource(R.color.bg);
                        mVpContent.setCurrentItem(4, false);
                        break;
                }
            }
        });
//        mVpContent.setOnPageChangeListener(new TabOnPageChangeListener());
    }


    private class TabOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        // 透明状态栏
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    }
//                    tintManager.setStatusBarAlpha(0.0f);
                    mRbHome.setChecked(true);
                    break;
                case 1:
                    mRbInvest.setChecked(true);
                    break;
                case 2:
                    mRbMarket.setChecked(true);
                    break;
                case 3:
                    mRbFind.setChecked(true);
                    break;
                case 4:
                    mRbMe.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }


    private class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }

    /**
     * 关闭程序提醒
     */
    private long keyBackClickCount;

    public boolean dispatchKeyEvent(KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                ) {
            switch ((int) keyBackClickCount++) {
                case 0:
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            keyBackClickCount = 0;
                        }
                    }, 3000);
                    break;
                case 1:
                    killAll();
                    this.finish();
                    break;
                default:
                    break;
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //viewpager中Fragment跳转，需要改变的东西，传入进去
    public interface Fragment2Fragment {
        public void gotoFragment(ViewPager viewPager, RadioGroup radioGroup, View view, RadioButton radioButton);
    }
    //持有当前Fragment
    private Fragment2Fragment fragment2Fragment;

    public void setFragment2Fragment(Fragment2Fragment fragment2Fragment) {
        this.fragment2Fragment = fragment2Fragment;
    }
    //Fragment的回调方法
    public void forSkip() {
        if (fragment2Fragment != null) {
            fragment2Fragment.gotoFragment(mVpContent, mRgMenu, mMainXian, mRbInvest);
        }
    }
}
