package com.huajiao.parkingsystem.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huajiao.parkingsystem.Ben.SearchData;
import com.huajiao.parkingsystem.Ben.SystemeMessageData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

import org.w3c.dom.Text;

public class ParkingDetails extends BaseActivity implements View.OnClickListener{
    private TextView parking_item_name;
    private TextView residue_content;
    private TextView distance_number;
    private TextView free_duration_content;
    private TextView charge_rules_content;
    private TextView common_content;
    private TextView no_common_content;
    private Button common_subscribe_btn;
    private Button no_common_subscribe_btn;
    private SearchData data;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.parking_details;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        data = (SearchData) getIntent().getSerializableExtra("SearchData");
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        common_subscribe_btn=findViewById(R.id.common_subscribe_btn);
        no_common_subscribe_btn=findViewById(R.id.no_common_subscribe_btn);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        no_common_subscribe_btn.setOnClickListener(this);
        common_subscribe_btn.setOnClickListener(this);

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
            case R.id.common_subscribe_btn:
                Intent intent =new Intent(ParkingDetails.this,ParkingSpaceDetails.class);
                intent.putExtra("buttonType",0);
                openActivity(intent);
                break;

            case R.id.no_common_subscribe_btn:
                Intent intents =new Intent(ParkingDetails.this,ParkingSpaceDetails.class);
                intents.putExtra("buttonType",1);
                openActivity(intents);
                break;
        }
    }
}
