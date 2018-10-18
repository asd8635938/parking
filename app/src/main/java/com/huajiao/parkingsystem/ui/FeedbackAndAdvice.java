package com.huajiao.parkingsystem.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.dialog.DialogUtils;
import com.huajiao.parkingsystem.tools.CheckUtil;

import java.io.File;

public class FeedbackAndAdvice extends BaseActivity implements View.OnClickListener{

    private EditText feedback_content_et;
    private ImageView feedback_content;
    private ImageView delete_feedback;
    private ImageButton up_load_btn;
    private Button submit_btn;
    private File file;
    private String feedText;
    private boolean isImage;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.feedback_and_advice;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {

    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        isImage=false;
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("反馈建议");
        feedback_content_et=findViewById(R.id.feedback_content_et);
        feedback_content=findViewById(R.id.feedback_content);
        delete_feedback=findViewById(R.id.delete_feedback);
        up_load_btn=findViewById(R.id.up_load_btn);
        submit_btn=findViewById(R.id.submit_btn);
        delete_feedback.setVisibility(View.GONE);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        delete_feedback.setOnClickListener(this);
        up_load_btn.setOnClickListener(this);
        submit_btn.setOnClickListener(this);
        feedback_content_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                feedText=s.toString();
            }
        });
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
            case R.id.delete_feedback:
                feedback_content.setImageResource(R.mipmap.white);
                delete_feedback.setVisibility(View.GONE);
                isImage=false;
                break;
            case R.id.up_load_btn:
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
            case R.id.submit_btn:
                if(!CheckUtil.checkString(feedText)){
                    showToast("请输入意见");
                    return;
                }
                if (!isImage){
                    // 不传给服务器
                }else{

                }
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
                feedback_content.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                delete_feedback.setVisibility(View.VISIBLE);
                isImage=true;
            }

            if (requestCode == 2 && data != null) {
                isImage=true;
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePathColumns[0]);
                String imagePath = c.getString(columnIndex);
                feedback_content.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                delete_feedback.setVisibility(View.VISIBLE);
                c.close();
            }

        }
    }
}
