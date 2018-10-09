package com.huajiao.parkingsystem.ui;


import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayActivity extends BaseActivity {

    @BindView(R.id.titleName)
    TextView titleName;
    @BindView(R.id.titleLife)
    ImageView titleLife;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;

    @Override
    protected int getViewContentId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.title));
        titleName.setText("支付宝提现");
        titleLife.setImageResource(R.mipmap.left);

        initOnClick();
    }

    private void initOnClick() {
        titleLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void getInternetData() {

    }
}
