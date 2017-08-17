package com.jdyy.ytwp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.adapter.GuideAdapter;
import com.jdyy.ytwp.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 引导界面
 * Created by Administrator on 2016/8/26 0026.
 */
public class GuideActivity extends BaseActivity {
    @Bind(R.id.vp_guide)
    ViewPager mVpGuide;
    //图片资源
    private int[] resIds = {R.mipmap.gift01, R.mipmap.gift02, R.mipmap.gift03, R.mipmap.gift04};

    private List<ImageView> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    /**
     * 初始化引导图片和引导点
     */
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < resIds.length; i++) {
            /**
             * 动态添加引导图片
             */
            ImageView img = new ImageView(this);
            img.setImageResource(resIds[i]);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            RelativeLayout.LayoutParams lpImg = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            img.setLayoutParams(lpImg);
            list.add(img);
        }

        list.get(resIds.length - 1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.put(GuideActivity.this, "isFirst", true);
                goToNextActivity(MainActivity.class);
            }
        });
    }

    private void initView() {
        GuideAdapter adapter = new GuideAdapter(list);
        mVpGuide.setAdapter(adapter);
        mVpGuide.setPageTransformer(false, new ZoomOutPageTransformer());
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    view.setAlpha(0);
                }

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        view.setTranslationX(horzMargin - vertMargin / 2);
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        view.setTranslationX(-horzMargin + vertMargin / 2);
                    }
                }

                // Scale the page down (between MIN_SCALE and 1)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    view.setScaleX(scaleFactor);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    view.setScaleY(scaleFactor);
                }

                // Fade the page relative to its size.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    view.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));
                }

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    view.setAlpha(0);
                }
            }
        }
    }
}
