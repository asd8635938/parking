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
        setTitleText("支付宝提现");
        initOnClick();
    }

    private void initOnClick() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void getInternetData() {

    }
}
