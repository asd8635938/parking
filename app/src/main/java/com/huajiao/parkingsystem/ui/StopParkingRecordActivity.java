package com.huajiao.parkingsystem.ui;

import android.view.View;
import android.widget.LinearLayout;

import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class StopParkingRecordActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout stop_record;
    private LinearLayout reservation_stop_record;
    private LinearLayout exception_stop_parking;
    private LinearLayout invoice;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.stop_parking_record;
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
        stop_record=findViewById(R.id.stop_record);
        reservation_stop_record=findViewById(R.id.reservation_stop_record);
        exception_stop_parking=findViewById(R.id.exception_stop_parking);
        invoice=findViewById(R.id.invoice);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        stop_record.setOnClickListener(this);
        reservation_stop_record.setOnClickListener(this);
        exception_stop_parking.setOnClickListener(this);
        invoice.setOnClickListener(this);
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
            case R.id.stop_record:
                openActivity(StopParkingOrderActivity.class);
                break;
            case R.id.reservation_stop_record:
                break;
            case R.id.exception_stop_parking:
                break;
            case R.id.invoice:
                break;
        }
    }
}
