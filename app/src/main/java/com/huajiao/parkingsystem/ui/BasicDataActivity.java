package com.huajiao.parkingsystem.ui;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.dialog.DialogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicDataActivity extends BaseActivity implements View.OnClickListener{

    private ImageView replace_btn; // 头像
    private ImageView head_portrait; // 头像控件
    private ImageView gender_btn; // 男女
    private ImageView iphone_number_btn; // 手机号
    private TextView iphone_number_text; // 手机号
    private ImageView my_id_card_btn; // 身份证
    private TextView  my_id_card_text; // 身份证
    private ImageView  my_address_btn; // 我的地址
    private TextView  my_address_text; // 我的地址
    private ImageView  car_number_btn; // 车牌号
    private TextView  car_number_text; // 车牌号
    private TextView gender_text; // 男女
    private TextView name_content; // 昵称
    private File file;

    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.basic_data;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData(){

    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("个人资料");
        replace_btn=findViewById(R.id.replace_btn);
        gender_btn=findViewById(R.id.gender_btn);
        name_content=findViewById(R.id.name_content);
        iphone_number_btn=findViewById(R.id.iphone_number_btn);
        iphone_number_text=findViewById(R.id.iphone_number_text);
        my_id_card_btn=findViewById(R.id.my_id_card_btn);
        my_id_card_text=findViewById(R.id.my_id_card_text);
        my_address_btn=findViewById(R.id.my_address_btn);
        my_address_text=findViewById(R.id.my_address_text);
        car_number_btn=findViewById(R.id.car_number_btn);
        car_number_text=findViewById(R.id.car_number_text);
        gender_text=findViewById(R.id.gender_text);
        head_portrait=findViewById(R.id.head_portrait);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        replace_btn.setOnClickListener(this);
        gender_btn.setOnClickListener(this);
        name_content.setOnClickListener(this);
        iphone_number_btn.setOnClickListener(this);
        my_id_card_btn.setOnClickListener(this);
        my_address_btn.setOnClickListener(this);
        car_number_btn.setOnClickListener(this);
    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.replace_btn:
                DialogUtils.showSelectCameraDialog(this, getWindow().getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(this, 40), new DialogUtils.ShowCameraCallBack() {
                            @Override
                            public void TopClick(Dialog dialog) {
                                  applyWritePermission();
                            }

                            @Override
                            public void CenterClick(Dialog dialog) {
                                //调用相册
                                Intent intent = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                openForResultActivity(intent, 2);
                                dialog.dismiss();
                            }

                            @Override
                            public void CancelClick(Dialog dialog) {
                                dialog.dismiss();
                            }
                        }
                );
                break;
             case  R.id.gender_btn:
                 DialogUtils.showSelectGenderDialog(this, getWindow().getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(this, 40), new DialogUtils.ShowGenderCallBack() {
                             @Override
                             public void ManClick(Dialog dialog) {
                                 gender_text.setText("男");
                                 dialog.dismiss();
                             }

                             @Override
                             public void femaleClick(Dialog dialog) {
                                 gender_text.setText("女");
                                 dialog.dismiss();
                             }
                         }
                 );
                break;
            case R.id.name_content:
                showEditDialog(name_content);
                break;
            case R.id.iphone_number_btn:
                showEditDialog(iphone_number_text);
                break;
            case R.id.my_id_card_btn:
                showEditDialog(my_id_card_text);
                break;
            case R.id.my_address_btn:
                showEditDialog(my_address_text);
                break;
            case R.id.car_number_btn:
                showEditDialog(car_number_text);
                break;
        }
    }
    private void useCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();

        //改变Uri  com.huajiao.parkingsystem.fileprovider注意和xml中的一致
        Uri uri = FileProvider.getUriForFile(this, "com.huajiao.parkingsystem.fileprovider", file);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        openForResultActivity(intent, 1);
    }

    public void applyWritePermission() {

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= 23) {
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check == PackageManager.PERMISSION_GRANTED) {
                //调用相机
                useCamera();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
            useCamera();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回数据 
            if(requestCode == 1){
                head_portrait.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            }

            if (requestCode == 2 && data != null) {
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePathColumns[0]);
                String imagePath = c.getString(columnIndex);
                head_portrait.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                c.close();
            }

        }
    }
    private void showEditDialog(TextView textView){
        DialogUtils.showBasicDialog(this, getWindow().getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(this, 40), new DialogUtils.ShowEditCallBack() {
            @Override
            public void LeftClick(Dialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void RightClick(Dialog dialog,TextView textView,String s) {
                dialog.dismiss();
                textView.setText(s);
            }
        },textView);
    }
}
