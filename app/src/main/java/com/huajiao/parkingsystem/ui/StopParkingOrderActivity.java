package com.huajiao.parkingsystem.ui;

import android.content.Intent;
import android.widget.ListView;

import com.huajiao.parkingsystem.Ben.StopParkingOrderData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.adapter.StopParkingOrderAdapter;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.interfaceback.StopParkingOrderClick;

import java.util.ArrayList;
import java.util.List;

public class StopParkingOrderActivity extends BaseActivity implements StopParkingOrderClick {
    private StopParkingOrderAdapter mAdapter;
    private List<StopParkingOrderData> mList = new ArrayList<>();
    private ListView list_view;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.stop_parking_order;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        for (int  i=0;i<10;i++){
            StopParkingOrderData data= new StopParkingOrderData();
            data.setTime("2018-03-04"+i);
            boolean use=(i+1)%2==0?true:false;
            data.setUse(use);
            mList.add(data);
        }
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        list_view=findViewById(R.id.list_view);
        mAdapter=new StopParkingOrderAdapter(this,this);
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
    public void clickBack(StopParkingOrderData data) {
        Intent intent =new Intent(StopParkingOrderActivity.this,StopOrderDetailsActivity.class);
        intent.putExtra("state",data.isUse());
        openActivity(intent);

    }
}
