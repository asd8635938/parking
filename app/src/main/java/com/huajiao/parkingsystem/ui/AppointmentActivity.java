package com.huajiao.parkingsystem.ui;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.AppLaunchChecker;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.dialog.DelayedDialog;
import com.huajiao.parkingsystem.dialog.DialogUtils;
import com.huajiao.parkingsystem.dialog.ParkingDetailsDialog;
import com.huajiao.parkingsystem.dialog.TimeKeepingPayDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentActivity extends BaseActivity {
    private int type;
    @BindView(R.id.textView_yanChang)
    TextView textView_yanChang;
    @BindView(R.id.textView_daoDa)
    TextView textView_daoDa;
    @BindView(R.id.textView_canle)
    TextView textView_canle;
    @BindView(R.id.textView_type)
    TextView textView_type;
    @BindView(R.id.button_layout)
    LinearLayout button_layout;

    private  DelayedDialog dialog;
    private TimeKeepingPayDialog tDialog;
    private List<String> whenData =new ArrayList<>();
    private List<String> secondsData=new ArrayList<>();
    private String when;
    private String seconds;

    @Override
    protected int getViewContentId() {
        return R.layout.activity_appointment;
    }

    @Override
    protected void initData() {
        type=getIntent().getIntExtra("type",0);
        for (int i =0; i<24;i++){
            whenData.add((i+1)+"");
        }
        for (int i = 0; i < 6; i++) {
            secondsData.add(((i+1)*10)+"");
        }
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("订单详情");
        dialog=new DelayedDialog(AppointmentActivity.this);
        dialog.setWhenData(whenData);
        dialog.setSecondsData(secondsData);
        tDialog=new TimeKeepingPayDialog(this);
        tDialog.setCoin(50);
        if (type==0){
            button_layout.setVisibility(View.VISIBLE);
            textView_canle.setVisibility(View.VISIBLE);
        }else{
            button_layout.setVisibility(View.GONE);
            textView_canle.setVisibility(View.GONE);
        }
        switch (type){
            case 0:
                textView_type.setTextColor(Color.parseColor("#27c38a"));
                textView_type.setText("预约中");
                break;
            case 1:
                textView_type.setTextColor(Color.parseColor("#898989"));
                textView_type.setText("已到达");
                break;
            case 2:
                textView_type.setTextColor(Color.parseColor("#898989"));
                textView_type.setText("已取消");
                break;
            case 3:
                textView_type.setTextColor(Color.parseColor("#898989"));
                textView_type.setText("已超时");
                break;
        }
    }

    @Override
    protected void bindEvent() {
        textView_canle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showDialog(false,AppointmentActivity.this, getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(AppointmentActivity.this,40), "提示", "确定取消预约吗？", new DialogUtils.ShowDialogCallBack() {
                    @Override
                    public void LeftClick(Dialog dialog) {
                        dialog.dismiss();
                    }
                    @Override
                    public void RightClick(Dialog dialog) {
                        dialog.dismiss();
                        // 服务器返回取消成功后回到预约停车页面
//                        openActivity(StopItActivity.class);

                    }
                });
            }
        });

        textView_daoDa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showDialog(false,AppointmentActivity.this, getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(AppointmentActivity.this,40), "提示", "确定后车位锁将自动降下", new DialogUtils.ShowDialogCallBack() {
                    @Override
                    public void LeftClick(Dialog dialog) {

                        dialog.dismiss();
                    }
                    @Override
                    public void RightClick(Dialog dialog) {
                        dialog.dismiss();
                        openActivity(ParkingSpaceLockSucc.class);
                    }
                });
            }
        });

        textView_yanChang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        dialog.setClicklistener(new DelayedDialog.ClickListenerInterface() {
            @Override
            public void doConfirm(String when,String seconds) {
                AppointmentActivity.this.when=when;
                AppointmentActivity.this.seconds=seconds;
                dialog.dismiss();
                tDialog.show();
            }
        });
        tDialog.setClicklistener(new TimeKeepingPayDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                tDialog.dismiss();
            }

            @Override
            public void openSelcetDiscountActivity() {
                Intent intent = new Intent();
                intent.setClass(AppointmentActivity.this,SelectDiscountActivity.class);
                openForResultActivity(intent,1);
            }
        });
    }

    @Override
    protected void getInternetData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tDialog.setDiscountCouponCoin(data.getExtras().getInt("payCoin"));
    }
}
