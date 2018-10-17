package com.huajiao.parkingsystem.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
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
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("车位详情");
        common_subscribe_btn=findViewById(R.id.common_subscribe_btn);
        no_common_subscribe_btn=findViewById(R.id.no_common_subscribe_btn);
        parking_item_name=findViewById(R.id.parking_item_name);
        residue_content=findViewById(R.id.residue_content);
        distance_number=findViewById(R.id.distance_number);
        free_duration_content=findViewById(R.id.free_duration_content);
        charge_rules_content=findViewById(R.id.charge_rules_content);
        common_content=findViewById(R.id.common_content);
        no_common_content=findViewById(R.id.no_common_content);
        setTextData();
    }

    private void setTextData() {
        if(data!=null){
            parking_item_name.setText(data.getName());
            residue_content.setText(data.getTotalCommonContent());
            distance_number.setText(data.getDistanceNumber()+"米");
            free_duration_content.setText(data.getFreeDurationContent());
            charge_rules_content.setText(data.getChargeRules());
            common_content.setText(data.getResidueContent());
            no_common_content.setText(data.getResidueNoCommonContent());
        }
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
