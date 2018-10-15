package com.huajiao.parkingsystem.ui;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.Ben.DiscountCouponData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.adapter.SelectDiscountCouponAdapter;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.interfaceback.SelectDiscoutClick;

import java.util.ArrayList;
import java.util.List;

public class SelectDiscountActivity extends BaseActivity implements SelectDiscoutClick {
    private ImageButton no_use_btn;
    private ListView list_view;
    private Button confirm;
    private SelectDiscountCouponAdapter mAdapter;
    private List<DiscountCouponData> mList = new ArrayList<>();
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.select_discount_coupon;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        for (int i=0; i<5;i++){
            DiscountCouponData data = new DiscountCouponData();
            data.setCanUse(false);
            data.setCouponId(i+"");
            data.setMoney("1008"+i);
            data.setTime("2018-10-12");
            mList.add(data);
        }
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("选择优惠券");
        list_view=findViewById(R.id.list_view);
        mAdapter=new SelectDiscountCouponAdapter(this,this);
        mAdapter.setDate(mList);
        list_view.setAdapter(mAdapter);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {

    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }

    @Override
    public void clickBack(String couponId, DiscountCouponData data, boolean isSelect) {

    }
}
