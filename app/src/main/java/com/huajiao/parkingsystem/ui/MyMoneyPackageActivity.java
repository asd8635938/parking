package com.huajiao.parkingsystem.ui;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class MyMoneyPackageActivity extends BaseActivity implements View.OnClickListener{

   private Button running_record_btn;
   private LinearLayout pay_coin;
   private LinearLayout withdrawal;
   private LinearLayout coupons;

    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.my_money_package;
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
        running_record_btn=findViewById(R.id.running_record_btn);
        pay_coin=findViewById(R.id.pay_coin);
        withdrawal=findViewById(R.id.withdrawal);
        coupons=findViewById(R.id.coupons);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        running_record_btn.setOnClickListener(this);
        pay_coin.setOnClickListener(this);
        withdrawal.setOnClickListener(this);
        coupons.setOnClickListener(this);
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
            case R.id.running_record_btn:
                openActivity(RunningRecordActivity.class);
                break;
            case R.id.pay_coin:
                openActivity(PayPageActivity.class);
                break;
            case R.id.withdrawal:
                openActivity(WithdrawalActivity.class);
                break;
            case R.id.coupons:
                openActivity(CouponActivity.class);
                break;
        }
    }
}
