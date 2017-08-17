package com.jdyy.ytwp.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.ytwp.R;
import com.jdyy.ytwp.activity.ChangeActivity;
import com.jdyy.ytwp.activity.ErWeiMaActivity;
import com.jdyy.ytwp.activity.FeedBackActivity;
import com.jdyy.ytwp.activity.GestureActivity;
import com.jdyy.ytwp.activity.InfoActivity;
import com.jdyy.ytwp.activity.LoginActivity;
import com.jdyy.ytwp.activity.RenZhengActivity;
import com.jdyy.ytwp.utils.Bimp;
import com.jdyy.ytwp.utils.FileUtils;
import com.jdyy.ytwp.utils.ImageCompress;
import com.jdyy.ytwp.utils.SPUtils;
import com.jdyy.ytwp.utils.T;
import com.jdyy.ytwp.views.CircleImageView;
import com.jdyy.ytwp.views.SelectPicDialog;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {


    boolean isOpen;
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.civ_tou)
    CircleImageView mCivTou;
    @Bind(R.id.pick_img)
    ImageView mPickImg;
    @Bind(R.id.fh_name)
    TextView mFhName;
    @Bind(R.id.tv_zh)
    TextView mTvZh;
    @Bind(R.id.ll_ewm)
    LinearLayout mLlEwm;
    @Bind(R.id.tv_renzhen)
    TextView mTvRenzhen;
    @Bind(R.id.ll_renzhen)
    LinearLayout mLlRenzhen;
    @Bind(R.id.ll_info)
    LinearLayout mLlInfo;
    @Bind(R.id.ll_xiugai)
    LinearLayout mLlXiugai;
    @Bind(R.id.iv_shoushi)
    ImageView mIvShoushi;
    @Bind(R.id.ll_shoushi)
    LinearLayout mLlShoushi;
    @Bind(R.id.tv_about)
    TextView mTvAbout;
    @Bind(R.id.ll_about)
    LinearLayout mLlAbout;
    @Bind(R.id.ll_problem)
    LinearLayout mLlProblem;
    @Bind(R.id.tv_phone)
    TextView mTvPhone;
    @Bind(R.id.ll_phone)
    LinearLayout mLlPhone;
    @Bind(R.id.ll_feedback)
    LinearLayout mLlFeedback;
    @Bind(R.id.btn_exit)
    Button mBtnExit;
    @Bind(R.id.fragment_me)
    RelativeLayout mFragmentMe;


    //相片的路径
    private String picPath = "";

    private Uri photoUri;
    //拍照获取相片
    public static final int SELECT_FROM_TAKE_PHOTO = 1;
    //相册选择相片
    public static final int SELECT_FROM_PICK_PHOTO = 2;

    private SelectPicDialog menuView;
    private File tempFile1;
    private String imageName1 = "";
    private Uri uri;  //图片保存uri
    private File scaledFile;
    private float dp;
    private String fanhui;
    private static final int TAKE_PICTURE = 4;
    private static final int REQUEST_IMAGE_ALBUM = 5;
    private static final int PHOTO_REQUEST_CUT = 6;

    private LayoutInflater mInflater;
    View mRootView;

    private void setlistener() {
        mCivTou.setOnClickListener(this);
        mPickImg.setOnClickListener(this);
        mLlEwm.setOnClickListener(this);
        mLlRenzhen.setOnClickListener(this);
        mLlInfo.setOnClickListener(this);
        mLlXiugai.setOnClickListener(this);
        mIvShoushi.setOnClickListener(this);
        mLlAbout.setOnClickListener(this);
        mLlProblem.setOnClickListener(this);
        mLlPhone.setOnClickListener(this);
        mLlFeedback.setOnClickListener(this);
        mBtnExit.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        isOpen = (boolean) SPUtils.get(getContext(), "isOpen", false);
        initView();
        setlistener();

        return rootView;
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.dl_bg);
        }
        Resources resource = getActivity().getBaseContext().getResources();
        ColorStateList csl = resource.getColorStateList(R.color.zc_bg);
        if (csl != null) {
            mTitle.setTextColor(csl);
        }
        mRlHead.setBackgroundResource(R.color.dl_bg);
        mTitle.setText(R.string.me);

        if (isOpen) {
            mIvShoushi.setImageResource(R.mipmap.wd_on);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civ_tou:
            case R.id.pick_img:
                ShowMenu();
                break;
            case R.id.ll_ewm:
                goToActivity(ErWeiMaActivity.class);
                break;
            case R.id.ll_renzhen:
                Intent intent = new Intent();
                intent.setClass(getContext(), RenZhengActivity.class);//账号设置
                startActivityForResult(intent, 2);
                break;
            case R.id.ll_info:
                goToActivity(InfoActivity.class);
                break;
            case R.id.ll_xiugai:
                goToNextActivity(ChangeActivity.class);

                break;
            case R.id.iv_shoushi:
                if (!isOpen) {
                    mIvShoushi.setImageResource(R.mipmap.wd_on);
                    Intent intent1 = new Intent(getContext(), GestureActivity.class);
                    intent1.putExtra("flag", "1");
                    startActivity(intent1);
                    isOpen = true;
                } else {
                    mIvShoushi.setImageResource(R.mipmap.wd_off);
                    SPUtils.put(getContext(), "inputCode", "");
                    SPUtils.put(getContext(), "isOpen", false);
                    isOpen = false;
                }
                break;
            case R.id.ll_about:

                break;

            case R.id.ll_problem:

                break;

            case R.id.ll_phone:
                ShowServiceDialog();
                break;

            case R.id.ll_feedback:
                goToActivity(FeedBackActivity.class);
                break;

            case R.id.btn_exit:
                goToNextActivity(LoginActivity.class);
                break;
        }
    }

    /**
     * 客服电话、联系方式
     */
    private void ShowServiceDialog() {
        mInflater = LayoutInflater.from(getContext());
        mRootView = mInflater.inflate(R.layout.layout_dialog_service, null);
        Button mBtnCall = (Button) mRootView.findViewById(R.id.btn_call);
        //必须要加style，不然会有白色头部
        final Dialog mDialog = new Dialog(getContext(), R.style.Dialog);
        mDialog.getWindow().setContentView(mRootView);
        mDialog.show();

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = mDialog.getWindow().getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        dialogWindow.setAttributes(p);
        //点击跳转拨号界面
        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                String string_phoneNum = mTvPhone.getText().toString();//得到电话号码
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + string_phoneNum));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //跳转系统拨号界面
                getContext().startActivity(intent);
            }
        });
    }

    private void ShowMenu() {
        menuView = new SelectPicDialog(getActivity(), itemOnClikListener);
        menuView.showAtLocation(mFragmentMe,
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private View.OnClickListener itemOnClikListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            menuView.dismiss();

            switch (v.getId()) {
                case R.id.takePhotoBtn:
                    changeHeadIcon(1);
                    break;
                case R.id.pickPhotoBtn:
                    changeHeadIcon(0);
                    break;
                case R.id.cancelBtn:
                    menuView.dismiss();
                    break;
            }
        }
    };

    @SuppressLint("SimpleDateFormat")
    private String getNowTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmssSS");
        return dateFormat.format(date);
    }

    private void changeHeadIcon(int item) {
        String saveDir;
        File imgFile;
        imageName1 = getNowTime() + ".png";
        if (item == 0) {
            saveDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ytwp/";
            imgFile = new File(saveDir);
            if (!imgFile.exists()) {
                imgFile.mkdir();
            }
            tempFile1 = new File(saveDir, imageName1);
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE_ALBUM);
        } else if (item == 1) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //                            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                saveDir = Environment.getExternalStorageDirectory() + "/ytwp/";
                imgFile = new File(saveDir);
                if (!imgFile.exists()) {
                    imgFile.mkdir();
                }
                tempFile1 = new File(saveDir, imageName1);
                Uri uri = Uri.fromFile(tempFile1);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, TAKE_PICTURE);
            }
        } else {
            T.showShort(getActivity(), "未找到存储卡，无法存储照片！");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK) {
                    mTvRenzhen.setText("已认证");
                } else {
                    mTvRenzhen.setText("未认证");
                }
                break;
            case TAKE_PICTURE:
                if (resultCode == -1) {
                    try {
                        crop(Uri.fromFile(tempFile1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                } else {
                    T.showShort(getActivity(), "未找到存储卡，无法存储照片！");
                }
                break;
            case REQUEST_IMAGE_ALBUM:
                if (null != data) {
                    Uri y;
                    y = data.getData();
                    try {
                        crop(y);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    Log.e("图片路径？？", data.getData() + "");
                }
            case PHOTO_REQUEST_CUT:
                if (null != data) {
                    uri = Uri.fromFile(tempFile1);
                    tempFile1 = ImageCompress.scal(uri);
                    Bitmap bitmap = Bimp.getLoacalBitmap(tempFile1.getAbsolutePath());
                    if (bitmap == null) {
                        mCivTou.setImageResource(R.mipmap.w_tx);
                    } else {
                        mCivTou.setImageBitmap(bitmap);
                    }
                }
                break;
        }
    }

    private void crop(Uri uri) throws IOException, URISyntaxException {

        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyyMMddhhmmss");
        String address = sDateFormat.format(new Date());
        if (!FileUtils.isFileExist("")) {
            FileUtils.createSDDir("");

        }
//        drr.add(FileUtils.SDPATH + address + ".JPEG");

        Uri imageUri = Uri.parse("file:///sdcard/formats/" + address + ".JPEG");

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
//        // aspectX aspectY 是宽高的比例
//        intent.putExtra("aspectX", 2);
//        intent.putExtra("aspectY", 1);
////        // outputX,outputY 是剪裁图片的宽高
//        intent.putExtra("outputX", 800);
//        intent.putExtra("outputY", 400);
//        intent.putExtra("return-data", false);
//        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        tempFile1 = new File(new URI(imageUri.toString()));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        intent.putExtra("return-data", false);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
