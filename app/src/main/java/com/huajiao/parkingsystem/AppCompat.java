package com.huajiao.parkingsystem;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.huajiao.parkingsystem.tools.PermissionUtil;

public class AppCompat extends AppCompatActivity {
    private String[] requestPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA};

    private PermissionUtil.PermissionTool permissionTool;
    private ImageView start;
    private boolean isGetPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        start=findViewById(R.id.start);
        isGetPermission=false;
        checkPermission();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGetPermission){
                    initView();
                }else {
                    checkPermission();
                }
            }
        });
    }

    private void initView() {
        Toast.makeText(this,"权限申请成功", Toast.LENGTH_LONG).show();
        startActivity(new Intent(AppCompat.this,MainActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionTool.onRequestPermissionResult(this, requestCode, permissions, grantResults);
    }

    private void checkPermission(){
        if (Build.VERSION.SDK_INT >= 23) { //针对6.0以后的版本加权限判断
            Toast.makeText(this,"申请权限", Toast.LENGTH_LONG).show();
            permissionTool = new PermissionUtil.PermissionTool(new PermissionUtil.PermissionListener() {
                @Override
                public void allGranted() {
                    initView();
                }
            });
            permissionTool.checkAndRequestPermission(AppCompat.this, requestPermissions);
        } else {
            Toast.makeText(this,"不申请权限", Toast.LENGTH_LONG).show();
            initView();
        }
    }
}
