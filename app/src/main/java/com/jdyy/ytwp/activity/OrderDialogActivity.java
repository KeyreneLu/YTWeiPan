package com.jdyy.ytwp.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdyy.ytwp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 下单界面
 * Created by Administrator on 2016/9/2 0002.
 */
public class OrderDialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.choice1)
    TextView mChoice1;
    @Bind(R.id.choice2)
    TextView mChoice2;
    @Bind(R.id.choice3)
    TextView mChoice3;
    @Bind(R.id.choice4)
    TextView mChoice4;
    @Bind(R.id.choice5)
    TextView mChoice5;
    @Bind(R.id.choice6)
    TextView mChoice6;
    @Bind(R.id.tv_coin)
    TextView mTvCoin;
    @Bind(R.id.tv_cash)
    TextView mTvCash;
    @Bind(R.id.tv_usemoney)
    TextView mTvUsemoney;
    @Bind(R.id.check_reduce)
    ImageView mCheckReduce;
    @Bind(R.id.tv_check)
    TextView mTvCheck;
    @Bind(R.id.check_add)
    ImageView mCheckAdd;
    @Bind(R.id.loss_reduce)
    ImageView mLossReduce;
    @Bind(R.id.tv_loss)
    TextView mTvLoss;
    @Bind(R.id.loss_add)
    ImageView mLossAdd;
    @Bind(R.id.btn_place)
    Button mBtnPlace;

    List<TextView> Tvlists = new ArrayList<>();
    int Chosen = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            this.setFinishOnTouchOutside(true);
        }
        setContentView(R.layout.layout_dialog_order);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.7
        getWindow().setAttributes(p);
        ButterKnife.bind(this);

        initView();
        setlistener();
    }

    private void initView() {
        Tvlists.add(mChoice1);
        Tvlists.add(mChoice2);
        Tvlists.add(mChoice3);
        Tvlists.add(mChoice4);
        Tvlists.add(mChoice5);
        Tvlists.add(mChoice6);

        mTvCash.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

    }


    private void setlistener() {
        mChoice1.setOnClickListener(this);
        mChoice2.setOnClickListener(this);
        mChoice3.setOnClickListener(this);
        mChoice4.setOnClickListener(this);
        mChoice5.setOnClickListener(this);
        mChoice6.setOnClickListener(this);
        mTvCash.setOnClickListener(this);
        mCheckReduce.setOnClickListener(this);
        mCheckAdd.setOnClickListener(this);
        mLossAdd.setOnClickListener(this);
        mLossReduce.setOnClickListener(this);
        mBtnPlace.setOnClickListener(this);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choice1:
                Chosen = 0;
                for (int i = 0; i < Tvlists.size(); i++) {
                    if (i == Chosen) {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_yellow);
                    } else {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_gray2);
                    }
                }
                break;
            case R.id.choice2:
                Chosen = 1;
                for (int i = 0; i < Tvlists.size(); i++) {
                    if (i == Chosen) {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_yellow);
                    } else {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_gray2);
                    }
                }
                break;
            case R.id.choice3:
                Chosen = 2;
                for (int i = 0; i < Tvlists.size(); i++) {
                    if (i == Chosen) {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_yellow);
                    } else {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_gray2);
                    }
                }
                break;
            case R.id.choice4:
                Chosen = 3;
                for (int i = 0; i < Tvlists.size(); i++) {
                    if (i == Chosen) {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_yellow);
                    } else {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_gray2);
                    }
                }
                break;
            case R.id.choice5:
                Chosen = 4;
                for (int i = 0; i < Tvlists.size(); i++) {
                    if (i == Chosen) {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_yellow);
                    } else {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_gray2);
                    }
                }
                break;
            case R.id.choice6:
                Chosen = 5;
                for (int i = 0; i < Tvlists.size(); i++) {
                    if (i == Chosen) {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_yellow);
                    } else {
                        Tvlists.get(i).setBackgroundResource(R.drawable.rect_gray2);
                    }
                }
                break;

            case R.id.tv_cash:
                Intent intent = new Intent(OrderDialogActivity.this, CashActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.check_add:
                break;
            case R.id.check_reduce:
                break;
            case R.id.loss_add:
                break;
            case R.id.loss_reduce:
                break;
            case R.id.btn_place:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK && null != data) {
                    mTvCoin.setText(data.getStringExtra("cash") + "元");
                }
        }
    }
}
