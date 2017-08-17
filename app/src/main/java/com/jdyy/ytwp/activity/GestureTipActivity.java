package com.jdyy.ytwp.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jdyy.ytwp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 提示创建手势密码界面
 * Created by Administrator on 2016/9/22 0022.
 */

public class GestureTipActivity extends BaseActivity {
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.btn_creating_gestures)
    Button mBtnCreatingGestures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesturetip);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setToolbar1();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setBackgroundResource(R.color.black90);
        }

        mBtnCreatingGestures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestureTipActivity.this,GestureActivity.class);
                intent.putExtra("flag","0");
                startActivity(intent);
                finish();
            }
        });
    }

}
