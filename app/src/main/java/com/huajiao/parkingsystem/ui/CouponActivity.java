package com.huajiao.parkingsystem.ui;


import android.util.TypedValue;

import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class CouponActivity extends BaseActivity {

    @Override
    protected int getViewContentId() {
        return R.layout.activity_coupon;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {



    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void getInternetData() {

    }

    /**
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }
}
