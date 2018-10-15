package com.huajiao.parkingsystem.ui;


import android.app.Dialog;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.dialog.DialogUtils;

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

    @Override
    protected int getViewContentId() {
        return R.layout.activity_appointment;
    }

    @Override
    protected void initData() {
        type=getIntent().getIntExtra("type",0);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("订单详情");
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
        initOnClick();
    }

    private void initOnClick() {

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
                        dissView();
                        textView_type.setText("已取消");
                        textView_type.setTextColor(getResources().getColor(R.color.text8b9aad));
                    }
                });
            }
        });

        textView_daoDa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dissView();
                textView_type.setText("已到达");
                textView_type.setTextColor(getResources().getColor(R.color.text8b9aad));
            }
        });

        textView_yanChang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showDialog(true,AppointmentActivity.this, getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(AppointmentActivity.this,40), "延长到达","*如需延长到达时间，需支付费用。", new DialogUtils.ShowDialogCallBack() {
                    @Override
                    public void LeftClick(Dialog dialog) {
                        dialog.dismiss();
                    }
                    @Override
                    public void RightClick(Dialog dialog) {
                        dialog.dismiss();
                        dissView();
                        textView_type.setText("已延时");
                        textView_type.setTextColor(getResources().getColor(R.color.text8b9aad));
                    }
                });
            }
        });
    }

    private void dissView() {
        textView_canle.setVisibility(View.GONE);
        textView_daoDa.setVisibility(View.GONE);
        textView_yanChang.setVisibility(View.GONE);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void getInternetData() {

    }
}
