package com.jdyy.ytwp.views;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.jdyy.ytwp.R;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class SelectPicDialog extends PopupWindow {
    private Button takePhotoBtn,picPhotoBtn,cancelBtn;
    private View mMenuView;
    public SelectPicDialog(Context context, View.OnClickListener itemOnClickListener) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.layout_dialog_choose, null);
        takePhotoBtn = (Button) mMenuView.findViewById(R.id.takePhotoBtn);
        picPhotoBtn = (Button) mMenuView.findViewById(R.id.pickPhotoBtn);
        cancelBtn = (Button) mMenuView.findViewById(R.id.cancelBtn);
//        设置点击事件
        takePhotoBtn.setOnClickListener(itemOnClickListener);
        picPhotoBtn.setOnClickListener(itemOnClickListener);
        cancelBtn.setOnClickListener(itemOnClickListener);

        this.setContentView(mMenuView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setAnimationStyle(R.style.SelectPicDialog);
//        ColorDrawable cd = new ColorDrawable(Color.GRAY);
//        this.setBackgroundDrawable(cd);
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.pop_layout).getHeight();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (y>height){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
}
